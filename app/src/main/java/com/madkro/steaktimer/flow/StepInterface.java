package com.madkro.steaktimer.flow;

import com.madkro.steaktimer.CookingFlow;

/**
 * Created by Marek on 2016-08-21.
 */
public interface StepInterface {
    void execute();
    void finishExecution();
    void preExecute();
    void setFlow(CookingFlow flow);
}
