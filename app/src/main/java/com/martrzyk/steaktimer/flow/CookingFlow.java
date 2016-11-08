package com.martrzyk.steaktimer.flow;

import android.content.Context;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.hanks.htextview.HTextView;
import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.flow.steps.Step;

import java.util.ArrayList;

import lombok.Getter;

/**
 * Created by Marek on 2016-08-21.
 */
public class CookingFlow {
    public static class CookingFlowBuilder {
        private ArrayList<Step> steps;
        private HTextView timerTextView;
        private TextView otherTextView;
        private StepsView stepView;
        private Context context;
        private long donenessId, type;

        public CookingFlowBuilder(Context context) {
            this.context = context;
        }

        public CookingFlow build() {
            return new CookingFlow(this);
        }

        public CookingFlowBuilder addSteps(Step... stepsArray) {
            if (stepsArray.length <= 0)
                return this;

            if (steps == null) steps = new ArrayList<>();

            for (Step step : stepsArray)
                steps.add(step);

            return this;
        }

        public CookingFlowBuilder setTimerTextView(HTextView timerTextView) {
            this.timerTextView = timerTextView;
            return this;
        }

        public CookingFlowBuilder setInformationTextView(TextView otherTextView) {
            this.otherTextView = otherTextView;
            return this;
        }

        public CookingFlowBuilder setStepView(StepsView stepView) {
            this.stepView = stepView;
            return this;
        }

        public CookingFlowBuilder setOptions(long pDoneness, long pType) {
            this.donenessId = pDoneness;
            this.type = pType;

            return this;
        }
    }

    /***********************************************/

    @Getter
    private Context context;
    @Getter
    private int position = -1;
    @Getter
    private long donenessId, typeId;
    @Getter
    private StepsView stepView;
    @Getter
    private ArrayList<Step> steps;
    @Getter
    private HTextView timerTextView;
    @Getter
    private TextView otherTextView;

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;

        for (Step step : this.steps)
            step.setFlow(this);
    }

    public String[] getStepsLabels(Context ctx) {
        String[] labels = new String[steps.size()];
        for (int i = 0; i < steps.size(); i++) {
            labels[i] = ctx.getString(steps.get(i).id);
        }

        return labels;
    }

    private CookingFlow(CookingFlowBuilder builder) {
        setSteps(builder.steps);
        this.timerTextView = builder.timerTextView;
        this.otherTextView = builder.otherTextView;
        this.donenessId = builder.donenessId;
        this.typeId = builder.type;
        this.stepView = builder.stepView;
        this.context = builder.context;
    }

    public int moveToNextStep() {
        //If we get to last element in steps list reset to 'empty'
        if (position + 1 > steps.size() - 1)
            position = -1;
        else //changing to next step
            position++;

        return position;
    }

    public int moveToPrevStep() {
        //If we get to last element in steps list reset to 'empty'
        if (position - 1 < 0)
            position = -1;
        else //changing to next step
            position--;

        return position;
    }

    public void executeStep(boolean forced) {
        if (position > 0) {
            Step prevStep = steps.get(position - 1);
            prevStep.finishExecution();
        }

        if (position > -1) {
            Step step = steps.get(position);
            step.donenessId = donenessId;
            step.typeId = typeId;
            step.execute();
        }

        if (position == -1) {
            timerTextView.setText(R.string.start_cooking);
        }

        stepView.setCompletedPosition(position);
        stepView.drawView();
    }
}
