package com.martrzyk.steaktimer.flow;


import com.martrzyk.steaktimer.R;

/**
 * Created by Marek on 2016-08-21.
 */
public class StepRotate extends Step {


    public StepRotate() {
        id = R.string.rotate;
    }

    @Override
    public void execute() {
        mFlow.getTimerTextView().setText(R.string.rotate);
    }
}
