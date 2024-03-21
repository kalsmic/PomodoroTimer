package com.github.kalsmic.android.pomodorotimer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.github.kalsmic.android.pomodorotimer.databinding.ActivityHomeBinding
import com.github.kalsmic.android.pomodorotimer.timer.TimerActivity
import kotlin.properties.Delegates

class HomeActivity : BaseActivity() {
    private lateinit var timerDuration: TextView
    var minDuration: Int = 1
    private var  maxDuration: Int = 60
    private var stepDuration: Int = 5
    private var currentDuration by Delegates.notNull<Int>()
    private lateinit var reduceTimerButton: Button
    private lateinit var increaseTimerButton: Button
    private lateinit var startTimerButton: Button
    private var completed by Delegates.notNull<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timerDuration = binding.textViewTimerDuration

        // set initial timer value
        currentDuration = minDuration
        setTimerDuration()

        reduceTimerButton = binding.buttonReduceDuration
        increaseTimerButton = binding.buttonIncreaseDuration
        startTimerButton = binding.buttonStartTimer
    }

    private fun setTimerDuration() {
        timerDuration.text = currentDuration.toString()
    }

    override fun onResume() {
        super.onResume()
        // check for timer status in intent
        val timerStatusIntent = intent
        completed = timerStatusIntent.getBooleanExtra("timerCompleted", false)

        // show notification to the user if timer is completed
        if (completed) {
            Toast.makeText(this, "Timer Completed", Toast.LENGTH_LONG).show()
            val sound = Sound(applicationContext)
            sound.playDefaultSound()
        }
    }

    /**
     * This method increases or reduces the timer duration
     *
     * @param view the increase or decrease timer button
     */
    fun changeTimerDuration(view: View) {
        if (view == increaseTimerButton && currentDuration + stepDuration <= maxDuration) {
            currentDuration += stepDuration
            toggleShowDurationButtons(currentDuration)
        } else if (view == reduceTimerButton && currentDuration - stepDuration >= minDuration) {
            currentDuration -= stepDuration
            toggleShowDurationButtons(currentDuration)
        }
    }


    /**
     * This method hides the increase and decrease timer buttons when the duration reaches the
     * threshold
     *
     * @param duration the length of the timer in minutes
     */
    private fun toggleShowDurationButtons(duration: Int) {
        if (duration <= minDuration) {
            reduceTimerButton.visibility = View.INVISIBLE
        } else {
            reduceTimerButton.visibility = View.VISIBLE
        }

        if (duration >= maxDuration) {
            increaseTimerButton.visibility = View.INVISIBLE
        } else {
            increaseTimerButton.visibility = View.VISIBLE
        }
        // SHOW CURRENT DURATION TO USER
        setTimerDuration()
    }


    /**
     * This method navigates to the TimerActivity
     *
     * @param view start timer view
     */
    fun goToStartTimer(view: View?) {
        val startTimerIntent = Intent()
        startTimerIntent.setClass(this, TimerActivity::class.java)

        startTimerIntent.putExtra("timerDuration", currentDuration.toLong())
        startActivity(startTimerIntent)
    }
}