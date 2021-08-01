package com.github.kalsmic.android.pomodorotimer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends BaseActivity {
    TextView timerDuration;
    Integer minDuration = 5, maxDuration = 60, stepDuration = 5, currentDuration;
    Button reduceTimerButton, increaseTimerButton, startTimerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        timerDuration = (TextView) findViewById(R.id.textView_timer_duration);

        // set initial timer value
        currentDuration = minDuration;
        timerDuration.setText(currentDuration.toString());
        reduceTimerButton = (Button) findViewById(R.id.button_reduce_duration);
        increaseTimerButton = (Button) findViewById(R.id.button_increase_duration);
        startTimerButton = (Button) findViewById(R.id.button_start_timer);
    }


    /**
     * This method increases or reduces the timer duration
     *
     * @param view the increase or decrease timer button
     */
    public void changeTimerDuration(View view) {

        if (view.equals(increaseTimerButton) && !((currentDuration + stepDuration) > maxDuration)) {
            currentDuration += stepDuration;
            toggleShowDurationButtons(currentDuration);
        } else if (view.equals(reduceTimerButton) && !((currentDuration - stepDuration) < minDuration)) {
            currentDuration -= stepDuration;
            toggleShowDurationButtons(currentDuration);
        }
    }


    /**
     * This method hides the increase and decrease timer buttons when the duration reaches the
     * threshold
     *
     * @param duration the length of the timer in minutes
     */
    private void toggleShowDurationButtons(int duration) {

        if (duration <= minDuration) {
            reduceTimerButton.setVisibility(View.INVISIBLE);
        } else {
            reduceTimerButton.setVisibility(View.VISIBLE);
        }

        if (duration >= maxDuration) {
            increaseTimerButton.setVisibility(View.INVISIBLE);
        } else {
            increaseTimerButton.setVisibility(View.VISIBLE);
        }
        // SHOW CURRENT DURATION TO USER
        timerDuration.setText(currentDuration.toString());

    }
}