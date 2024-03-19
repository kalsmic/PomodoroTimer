package com.github.kalsmic.android.pomodorotimer;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.kalsmic.android.pomodorotimer.databinding.ActivityNotificationsBinding;

import java.util.Objects;

public class NotificationsActivity extends AppCompatActivity {

    int currentNotificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNotificationsBinding binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // set back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Notifications");

        // create a sound object
        Sound sound = new Sound(getApplicationContext());

        // get default notification sound Id
        currentNotificationId = sound.getDefaultSoundId();

        ListView listViewSounds = binding.listViewSounds;

        // populate list view with sounds
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.soundsArray, android.R.layout.simple_list_item_single_choice);
        listViewSounds.setAdapter(adapter);

        // select default notification sound  in the list view
        listViewSounds.setItemChecked(currentNotificationId, true);

        // attach action to click event
        listViewSounds.setOnItemClickListener((parent, view, position, id) -> {
            sound.play(position);
            currentNotificationId = position;
        });

        Button saveSoundButton = binding.buttonSaveSound;

        saveSoundButton.setOnClickListener(v -> {
            sound.setDefaultSound(currentNotificationId);
            // Show notification to the user
            Toast.makeText(getApplicationContext(), "Sound Changed !", Toast.LENGTH_SHORT).show();
        });


    }

}