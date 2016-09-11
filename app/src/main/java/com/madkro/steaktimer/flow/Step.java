package com.madkro.steaktimer.flow;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;

import com.madkro.steaktimer.CookingFlow;

/**
 * Created by Marek on 2016-08-21.
 */
public abstract class Step implements StepInterface {
    public int id;
    protected CookingFlow mFlow;
    protected Handler timerHandler = new Handler();
    protected Runnable timerRunnable;
    protected long startTime = 0;
    public int donenessId;
    public int typeId;

    public Long getTiming() {
        return null;
    }

    @Override
    public void setFlow(CookingFlow flow) {
        this.mFlow = flow;
    }

    @Override
    public void finishExecution() {
        callNotifcation();
    }

    @Override
    public void preExecute() {
    }

    protected void callNotifcation()
    {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(mFlow.getContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
