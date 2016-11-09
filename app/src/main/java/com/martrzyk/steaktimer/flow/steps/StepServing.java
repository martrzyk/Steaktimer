package com.martrzyk.steaktimer.flow.steps;


import com.martrzyk.steaktimer.R;

/**
 * Created by Marek on 2016-08-21.
 */
public class StepServing extends Step {

    public StepServing() {
        id = R.string.serving;
    }

    @Override
    public void execute() {
        mFlow.getTimerTextView().animateText(mFlow.getContext().getString(R.string.serving));
    }


    @Override
    public Long getTiming() {
        return super.getTiming();
    }
}
