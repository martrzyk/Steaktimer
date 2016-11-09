package com.martrzyk.steaktimer.flow;

import android.content.Context;
import android.widget.TextView;

import com.anton46.stepsview.StepsView;
import com.hanks.htextview.HTextView;
import com.martrzyk.steaktimer.R;
import com.martrzyk.steaktimer.flow.steps.Step;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

@Getter
@Setter
@Builder
public class CookingFlow {
    private ArrayList<Step> steps;
    private Context context;
    private int position = -1;
    private long donenessId;
    private long typeId;

    private StepsView stepView;
    private HTextView timerTextView;
    private TextView otherTextView;

    public String[] getStepsLabels(Context ctx) {
        String[] labels = new String[steps.size()];
        for (int i = 0; i < steps.size(); i++) {
            labels[i] = ctx.getString(steps.get(i).id);
        }

        return labels;
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
            prevStep.finishExecution(forced);
        }

        if (position > -1) {
            Step step = steps.get(position);
            step.setFlow(this);
            step.donenessId = donenessId;
            step.typeId = typeId;
            step.execute();
        }

        if (position == -1) {
            timerTextView.animateText(getContext().getString(R.string.start_cooking));
        }

        stepView.setCompletedPosition(position);
        stepView.drawView();
    }
}
