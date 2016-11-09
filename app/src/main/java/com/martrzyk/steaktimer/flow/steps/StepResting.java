package com.martrzyk.steaktimer.flow.steps;


import com.martrzyk.steaktimer.R;

import java.util.Locale;

/**
 * Created by Marek on 2016-08-21.
 */
public class StepResting extends Step {

    public StepResting() {
        id = R.string.resting;
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

                mFlow.getTimerTextView().animateText(String.format(Locale.getDefault(), "%d : %02d", minutes, seconds));

                if (millis > 100)
                    timerHandler.postDelayed(this, 16); //we assume that refreshrate is at least 60fps which means 16ms per frame - no point of refreshing more often
                else
                    finishExecution(false);
            }
        };

        startTime = System.currentTimeMillis() + 1000 * getTiming();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    @Override
    public void finishExecution(boolean forced) {
        super.finishExecution(forced);

        timerHandler.removeCallbacks(timerRunnable);
    }

    @Override
    public Long getTiming() {
        return 601L;
    }
}
