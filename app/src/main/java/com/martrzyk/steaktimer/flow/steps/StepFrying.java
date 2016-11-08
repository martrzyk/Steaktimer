package com.martrzyk.steaktimer.flow.steps;


import com.martrzyk.steaktimer.R;

/**
 * Created by Marek on 2016-08-21.
 */
public class StepFrying extends Step {

    public StepFrying() {
        id = R.string.frying;
    }

    @Override
    public void execute() {
        timerRunnable = new Runnable() {
            @Override
            public void run() {
                long millis = startTime - System.currentTimeMillis();
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;

                mFlow.getTimerTextView().animateText(String.format("%d : %02d", minutes, seconds));

                if(millis > 100)
                    timerHandler.postDelayed(this, 16); //we assume that refreshrate is at least 60fps which means 16ms per frame - no point of refreshing more often
                else
                    finishExecution();
            }
        };

        startTime = System.currentTimeMillis() + 1000 * getTiming();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    @Override
    public void finishExecution() {
        super.finishExecution();

        timerHandler.removeCallbacks(timerRunnable);
    }

    @Override
    public Long getTiming() {
        if(donenessId == R.string.rare){ return 60L; }
        else if(donenessId == R.string.medium_rare){ return 90L; }
        else if(donenessId == R.string.medium){ return 120L; }
        else if(donenessId == R.string.well_done){ return 150L; }
        else return 60L;
    }
}
