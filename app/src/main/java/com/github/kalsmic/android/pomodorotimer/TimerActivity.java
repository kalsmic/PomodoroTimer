package com.github.kalsmic.android.pomodorotimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {
    long timeTotal, timeLeft;
    CountDownTimer countDownTimer;
    Button resumeTimerButton, pauseTimerButton, restartTimerButton, cancelTimerButton, goHomeButton;
    TextView textViewMinutes, textViewSeconds;
    Intent goToHomePage;
    LinearLayout timerDisplayLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // create intent to get values passed from Home Activity
        Intent startTimeIntent = getIntent();
        timeTotal = startTimeIntent.getLongExtra("timerDuration", 0) * 60000;

        // Retrieve references to views declared in activity_timer.xml
        textViewMinutes = (TextView) findViewById(R.id.textView_minutes);
        textViewSeconds = (TextView) findViewById(R.id.textView_seconds);
        resumeTimerButton = (Button) findViewById(R.id.button_resume_timer);
        pauseTimerButton = (Button) findViewById(R.id.button_pause_timer);
        restartTimerButton = (Button) findViewById(R.id.button_restart_timer);
        cancelTimerButton = (Button) findViewById(R.id.button_cancel_timer);
        goHomeButton = (Button) findViewById(R.id.button_go_home);
        timerDisplayLayout = (LinearLayout) findViewById(R.id.linearLayout_timer_display);

        // start the timer
        startTimer(timeTotal);

        // create intent for redirecting back to the home page.
        goToHomePage = new Intent();
        goToHomePage.setClass(this, HomeActivity.class);
    }


    /**
     * This methods restarts the timer countdown
     *
     * @param view is the restart timer view button
     */
    public void restartTimer(View view) {
        countDownTimer.cancel();
        startTimer(timeTotal);
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
        startTimer(timeLeft);
        // hide play button and show pause button
        resumeTimerButton.setVisibility(View.INVISIBLE);
        pauseTimerButton.setVisibility(View.VISIBLE);

        // set playing state colors
        timerDisplayLayout.setBackgroundColor(getResources().getColor(R.color.light_blue));
        textViewSeconds.setBackgroundColor(getResources().getColor(R.color.dark_blue));
        textViewMinutes.setBackgroundColor(getResources().getColor(R.color.dark_blue));

    }

    /**
     * This methods pauses the timer countdown
     *
     * @param view is the pause timer view button
     */
    public void pauseTimer(View view) {
        countDownTimer.cancel();
        // hide pause button and show resume button
        pauseTimerButton.setVisibility(View.INVISIBLE);
        resumeTimerButton.setVisibility(View.VISIBLE);
        // set playing state colors
        timerDisplayLayout.setBackgroundColor(getResources().getColor(R.color.light_orange));
        textViewSeconds.setBackgroundColor(getResources().getColor(R.color.dark_orange));
        textViewMinutes.setBackgroundColor(getResources().getColor(R.color.dark_orange));


    }

    /**
     * This methods cancels the timer and redirects back to the homepage
     *
     * @param view the cancel timer view button
     */
    public void cancelTimer(View view) {
        countDownTimer.cancel();
        // redirect to Homepage
        startActivity(goToHomePage);
    }


    /**
     * This methods creates a countDown Timer object
     *
     * @param millisInFuture duration of timer in milliseconds
     * @return the CountDownTimer object
     */
    public CountDownTimer getCountDownTimer(long millisInFuture) {
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                formatTimerDisplay(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                formatTimerDisplay(timeTotal);
                timerDisplayLayout.setBackgroundColor(getResources().getColor(R.color.light_green));
                textViewSeconds.setBackgroundColor(getResources().getColor(R.color.dark_green));
                textViewMinutes.setBackgroundColor(getResources().getColor(R.color.dark_green));
                Sound sound = new Sound(getApplicationContext());

                sound.playDefaultSound();
                Toast.makeText(getApplicationContext(), "Timer Completed", Toast.LENGTH_LONG).show();
                pauseTimerButton.setVisibility(View.INVISIBLE);
                cancelTimerButton.setVisibility(View.INVISIBLE);
                goHomeButton.setVisibility(View.VISIBLE);



                // indicate that timer completed.
//                goToHomePage.putExtra("timerCompleted", true);
                // redirect back to home page
//                startActivity(goToHomePage);
            }

        };
        return countDownTimer;
    }

    /**
     * This methods formats the time for display in form of minutes and seconds
     *
     * @param duration duration in milliseconds
     */
    public void formatTimerDisplay(long duration) {
        // Extract minutes from duration
        int minutes = (int) duration / 60000;

        // Display the minutes to the user
        textViewMinutes.setText(minutes + "");
        // Extract seconds from duration, excluding the minutes
        int seconds = (int) (duration / 1000) % 60;

        // Display the seconds to the user
        textViewSeconds.setText(seconds + "");
    }

    /**
     * This methods starts the counter
     *
     * @param duration is the length of the count down in milliseconds
     */
    public void startTimer(long duration) {
        getCountDownTimer(duration).start();
    }

    public void goToHomeScreen(View view) {
        // redirect back to home page
        startActivity(goToHomePage);
    }
}