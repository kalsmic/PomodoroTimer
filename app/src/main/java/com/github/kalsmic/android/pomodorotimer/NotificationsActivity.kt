package com.github.kalsmic.android.pomodorotimer

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.kalsmic.android.pomodorotimer.databinding.ActivityNotificationsBinding
import java.util.Objects

class NotificationsActivity : AppCompatActivity() {
    private var currentNotificationId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = "Notifications"

        // create a sound object
        val sound = Sound(applicationContext)

        // get default notification sound Id
        currentNotificationId = sound.defaultSoundId

        val listViewSounds = binding.listViewSounds

        // populate list view with sounds
        val adapter = ArrayAdapter.createFromResource(
                this, R.array.soundsArray, android.R.layout.simple_list_item_single_choice)
        listViewSounds.adapter = adapter

        // select default notification sound  in the list view
        listViewSounds.setItemChecked(currentNotificationId, true)

        // attach action to click event
        listViewSounds.onItemClickListener = OnItemClickListener { parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            sound.play(position)
            currentNotificationId = position
        }

        val saveSoundButton = binding.buttonSaveSound

        saveSoundButton.setOnClickListener { v: View? ->
            sound.defaultSound = currentNotificationId
            // Show notification to the user
            Toast.makeText(applicationContext, "Sound Changed !", Toast.LENGTH_SHORT).show()
        }
    }
}