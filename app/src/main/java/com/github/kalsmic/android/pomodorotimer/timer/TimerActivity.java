package com.github.kalsmic.android.pomodorotimer.timer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.github.kalsmic.android.pomodorotimer.HomeActivity;
import com.github.kalsmic.android.pomodorotimer.R;
import com.github.kalsmic.android.pomodorotimer.Sound;
import com.github.kalsmic.android.pomodorotimer.databinding.ActivityTimerBinding;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TimerActivity extends AppCompatActivity {
    Button resumeTimerButton, pauseTimerButton, restartTimerButton, cancelTimerButton, goHomeButton;
    TextView textViewMinutes, textViewSeconds;
    Intent goToHomePage;
    LinearLayout timerDisplayLayout;
    private TimerViewModel mTimerViewModel;
    private ActivityTimerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTimerViewModel = new ViewModelProvider(this).get(TimerViewModel.class);

        // Retrieve references to views declared in activity_timer.xml
        textViewMinutes = binding.textViewMinutes;
        textViewSeconds = binding.textViewSeconds;
        resumeTimerButton = binding.buttonResumeTimer;
        pauseTimerButton = binding.buttonPauseTimer;
        restartTimerButton = binding.buttonRestartTimer;
        cancelTimerButton = binding.buttonCancelTimer;
        goHomeButton = binding.buttonGoHome;
        timerDisplayLayout = binding.linearLayoutTimerDisplay;

        // create intent to get values passed from Home Activity
        Intent startTimeIntent = getIntent();
        long timeTotal = startTimeIntent.getLongExtra("timerDuration", 0) * 60000;

        mTimerViewModel.setMaxTime(timeTotal);
        mTimerViewModel.startTimer();


        mTimerViewModel.getTimeLeft().observe(this, this::formatTimerDisplay);
        mTimerViewModel.getIsComplete().observe(this, this::completeTimer);


        // create intent for redirecting back to the home page.
        goToHomePage = new Intent();
        goToHomePage.setClass(this, HomeActivity.class);


    }

    private void completeTimer(Boolean isComplete) {
        if (isComplete) {
            timerDisplayLayout.setBackgroundColor(getResources().getColor(R.color.light_green));
            textViewSeconds.setBackgroundColor(getResources().getColor(R.color.dark_green));
            textViewMinutes.setBackgroundColor(getResources().getColor(R.color.dark_green));
            Sound sound = new Sound(getApplicationContext());

            sound.playDefaultSound();
            Toast.makeText(getApplicationContext(), "Timer Completed", Toast.LENGTH_LONG).show();
            pauseTimerButton.setVisibility(View.INVISIBLE);
            cancelTimerButton.setVisibility(View.INVISIBLE);
            goHomeButton.setVisibility(View.VISIBLE);
        }

    }


    /**
     * This methods restarts the timer countdown
     *
     * @param view is the restart timer view button
     */
    public void restartTimer(View view) {
        mTimerViewModel.resetTimer();
        // hide play button and show pause button
        resumeTimerButton.setVisibility(View.INVISIBLE);
        restartTimerButton.setVisibility(View.VISIBLE);
        pauseTimerButton.setVisibility(View.VISIBLE);
    }

    /**
     * This methods resumes the timer countdown
     *
     * @param view is the resume timer view button
     */
    public void resumeTimer(View view) {
        mTimerViewModel.resumeTimer();
        // hide play button and show pause button
        resumeTimerButton.setVisibility(View.INVISIBLE);
        pauseTimerButton.setVisibility(View.VISIBLE);

        // set playing state colors
        timerDisplayLayout.setBackgroundColor(getResources().getColor(R.color.light_green));
        textViewSeconds.setBackgroundColor(getResources().getColor(R.color.dark_green));
        textViewMinutes.setBackgroundColor(getResources().getColor(R.color.dark_green));

    }

    /**
     * This methods pauses the timer countdown
     *
     * @param view is the pause timer view button
     */
    public void pauseTimer(View view) {
        mTimerViewModel.cancelTimer();
        // hide pause button and show resume button
        pauseTimerButton.setVisibility(View.INVISIBLE);
        resumeTimerButton.setVisibility(View.VISIBLE);
        // set playing state colors
        timerDisplayLayout.setBackgroundColor(getResources().getColor(R.color.light_red));
        textViewSeconds.setBackgroundColor(getResources().getColor(R.color.dark_red));
        textViewMinutes.setBackgroundColor(getResources().getColor(R.color.dark_red));


    }

    /**
     * This methods cancels the timer and redirects back to the homepage
     *
     * @param view the cancel timer view button
     */
    public void cancelTimer(View view) {
        mTimerViewModel.cancelTimer();
        // redirect to Homepage
        startActivity(goToHomePage);
    }


    /**
     * This methods formats the time for display in form of minutes and seconds
     *
     * @param duration duration in milliseconds
     */
    public void formatTimerDisplay(long duration) {

        // Extract minutes from duration
        long minutes = TimeUnit.MILLISECONDS.toMinutes(duration);

        // Display the minutes to the user
        textViewMinutes.setText(String.format(Locale.ROOT, "%02d", minutes));
        // Extract seconds from duration, excluding the minutes
        long seconds = (duration / 1000) % 60;

        // Display the seconds to the user
        textViewSeconds.setText(String.format(Locale.ROOT, "%02d", seconds));

    }


    public void goToHomeScreen(View view) {
        // redirect back to home page
        startActivity(goToHomePage);
    }
}