package com.martrzyk.steaktimer.flow.steps;

import com.martrzyk.steaktimer.flow.CookingFlow;

/**
 * Created by Marek on 2016-08-21.
 */
public interface StepInterface {
    void execute();
    void finishExecution(boolean forced);
    void preExecute();
    void setFlow(CookingFlow flow);
}
