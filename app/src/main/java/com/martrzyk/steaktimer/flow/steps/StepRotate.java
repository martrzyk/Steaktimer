package com.martrzyk.steaktimer.flow.steps;


import android.support.v4.content.ContextCompat;

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
        mFlow.getTimerTextView().animateText(mFlow.getContext().getString(R.string.rotate));
    }
}
