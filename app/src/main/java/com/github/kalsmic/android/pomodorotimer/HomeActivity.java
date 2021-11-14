package com.github.kalsmic.android.pomodorotimer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.kalsmic.android.pomodorotimer.databinding.ActivityHomeBinding;
import com.github.kalsmic.android.pomodorotimer.timer.TimerActivity;

public class HomeActivity extends BaseActivity {
    TextView timerDuration;
    Integer minDuration = 1, maxDuration = 60, stepDuration = 5, currentDuration;
    Button reduceTimerButton, increaseTimerButton, startTimerButton;
    Boolean completed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        timerDuration = binding.textViewTimerDuration;

        // set initial timer value
        currentDuration = minDuration;
        setTimerDuration();

        reduceTimerButton = binding.buttonReduceDuration;
        increaseTimerButton = binding.buttonIncreaseDuration;
        startTimerButton = binding.buttonStartTimer;


    }

    private void setTimerDuration() {
        timerDuration.setText(String.valueOf(currentDuration));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // check for timer status in intent
        Intent timerStatusIntent = getIntent();
        completed = timerStatusIntent.getBooleanExtra("timerCompleted", false);

        // show notification to the user if timer is completed
        if (completed) {
            Toast.makeText(this, "Timer Completed", Toast.LENGTH_LONG).show();
            Sound sound = new Sound(getApplicationContext());
            sound.playDefaultSound();
        }
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
        setTimerDuration();

    }


    /**
     * This method navigates to the TimerActivity
     *
     * @param view start timer view
     */
    public void goToStartTimer(View view) {
        Intent startTimerIntent = new Intent();
        startTimerIntent.setClass(this, TimerActivity.class);

        startTimerIntent.putExtra("timerDuration", currentDuration.longValue());
        startActivity(startTimerIntent);
    }
}