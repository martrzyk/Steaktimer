package com.martrzyk.steaktimer.flow;

import com.martrzyk.steaktimer.CookingFlow;

/**
 * Created by Marek on 2016-08-21.
 */
public interface StepInterface {
    void execute();
    void finishExecution();
    void preExecute();
    void setFlow(CookingFlow flow);
}
