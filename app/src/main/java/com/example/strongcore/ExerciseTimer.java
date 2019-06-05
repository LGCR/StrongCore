package com.example.strongcore;

import android.os.CountDownTimer;
import android.widget.TextView;

public class ExerciseTimer extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */

    private TextView timer;

    public ExerciseTimer(TextView timer, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.timer = timer;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long seconds = millisUntilFinished/1000;
        if (seconds >= 10) {
            timer.setText("00:" + seconds);
        } else {
            timer.setText("00:0" + seconds);
        }
    }

    @Override
    public void onFinish() {
        timer.setText("Descanse");

    }
}
