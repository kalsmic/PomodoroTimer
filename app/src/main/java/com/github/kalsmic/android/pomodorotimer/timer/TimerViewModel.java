package com.github.kalsmic.android.pomodorotimer.timer;

import android.os.CountDownTimer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TimerViewModel extends ViewModel {
    private final MutableLiveData<Long> mMillisUntilFinished = new MutableLiveData<>();
    private final MutableLiveData<Long> mMillisInFuture = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isComplete = new MutableLiveData<>();
    private CountDownTimer mCountDownTimer = null;


    /**
     * This methods creates a countDown Timer object
     *
     * @param millisInFuture duration of timer in milliseconds
     */

    private void startCountDown(long millisInFuture) {
        mCountDownTimer = new CountDownTimer(millisInFuture, 1000) {

            public void onTick(long millisUntilFinished) {
                mMillisUntilFinished.setValue(millisUntilFinished);
            }

            public void onFinish() {
                isComplete.setValue(true);
            }
        }.start();

    }

    /**
     * This methods starts the timer countdown
     */
    public void startTimer() {
        startCountDown(mMillisInFuture.getValue());
    }


    /**
     * This methods cancels the timer countdown
     */
    public void cancelTimer() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    /**
     * This methods pauses the timer countdown
     */
    public void resumeTimer() {
        startCountDown(mMillisUntilFinished.getValue());
    }

    /**
     * This methods restarts the timer countdown
     */
    public void resetTimer() {
        cancelTimer();
        startCountDown(mMillisInFuture.getValue());
    }


    /**
     * This methods returns an observable of the time left
     *
     * @return time left in Milliseconds
     */
    public LiveData<Long> getTimeLeft() {
        return mMillisUntilFinished;
    }

    /**
     * This method returns an observable of the timer complete status
     *
     * @return boolean observable of timer status
     */

    public LiveData<Boolean> getIsComplete() {
        return isComplete;
    }


    /**
     * Initializes the maximum timer time and time left
     *
     * @param milliseconds maximum time
     */
    public void setMaxTime(long milliseconds) {
        mMillisInFuture.setValue(milliseconds);
        mMillisUntilFinished.setValue(milliseconds);
    }

}
