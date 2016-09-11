package com.madkro.steaktimer;

import android.content.Context;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.madkro.steaktimer.flow.Step;

import java.util.ArrayList;

/**
 *
 * Created by Marek on 2016-08-21.
 */
public class CookingFlow {
    public static class CookingFlowBuilder {
        private ArrayList<Step> steps;
        private TextView timerTextView;
        private TextView otherTextView;
        private StepsView stepView;
        private Context context;
        private int donenessId;
        private int type;

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

        public CookingFlowBuilder setTimerTextView(TextView timerTextView) {
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

        public CookingFlowBuilder setOptions(int pDoneness, int pType) {
            this.donenessId = pDoneness;
            this.type = pType;

            return this;
        }
    }

    /***********************************************/

    private int position = -1;
    private ArrayList<Step> steps;
    private TextView mTimerTextView;
    private TextView mOtherTextView;
    private StepsView mStepView;
    private int donenessId;
    private int typeId;
    private Context context;

    public ArrayList<Step> getSteps() {
        return steps;
    }

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
        setTimerTextView(builder.timerTextView);
        setOtherTextView(builder.otherTextView);
        setDonenessId(builder.donenessId);
        setTypeId(builder.type);
        setStepView(builder.stepView);
        setContext(builder.context);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

        if(position == -1)
        {
            mTimerTextView.setText(R.string.start_cooking);
        }

        mStepView.setCompletedPosition(position);
        mStepView.drawView();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public StepsView getStepView() {
        return mStepView;
    }

    public void setStepView(StepsView pStepView) {
        this.mStepView = pStepView;
    }

    public TextView getTimerTextView() {
        return mTimerTextView;
    }

    public void setTimerTextView(TextView pTimerTextView) {
        this.mTimerTextView = pTimerTextView;
    }

    public TextView getOtherTextView() {
        return mOtherTextView;
    }

    public void setOtherTextView(TextView pOtherTextView) {
        this.mOtherTextView = pOtherTextView;
    }

    public int getDonenessId() {
        return donenessId;
    }

    public void setDonenessId(int donenessId) {
        this.donenessId = donenessId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
