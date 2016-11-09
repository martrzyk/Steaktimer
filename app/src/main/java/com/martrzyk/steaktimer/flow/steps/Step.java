package com.martrzyk.steaktimer.flow.steps;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;

import com.martrzyk.steaktimer.flow.CookingFlow;

import lombok.experimental.Builder;

public abstract class Step implements StepInterface {
    public int id;
    public long donenessId;
    public long typeId;

    CookingFlow mFlow;
    Handler timerHandler = new Handler();
    Runnable timerRunnable;
    long startTime = 0;

    public Long getTiming() {
        return null;
    }

    @Override
    public void setFlow(CookingFlow flow) {
        this.mFlow = flow;
    }

    @Override
    public void finishExecution(boolean forced) {
        if(!forced)
            callNotifcation();
    }

    @Override
    public void preExecute() {
    }

    protected void callNotifcation() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(mFlow.getContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
