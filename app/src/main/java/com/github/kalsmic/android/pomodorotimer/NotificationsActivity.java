package com.github.kalsmic.android.pomodorotimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    int currentNotificationId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // create shared preference file
        SharedPreferences soundPreferences = getSharedPreferences("SOUND_PREF", Context.MODE_PRIVATE);
        final SharedPreferences.Editor defaultSoundEditor = soundPreferences.edit();

        // get default notification sound from shared preferences
        if (soundPreferences.contains("soundId")) {
            currentNotificationId = soundPreferences.getInt("soundId", 0);
        }

        ListView listViewSounds = (ListView) findViewById(R.id.listView_sounds);

        // populate list view with sounds
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.soundsArray, android.R.layout.simple_list_item_single_choice);
        listViewSounds.setAdapter(adapter);

        // select default notification sound  in the list view
        listViewSounds.setItemChecked(currentNotificationId, true);

        Log.d("currentNotificationId", currentNotificationId + "");

        // attach action to click event
        listViewSounds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentNotificationId = position;
            }
        });

        Button saveSoundButton = (Button) findViewById(R.id.button_save_sound);

        saveSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // store value in shared prefs file.
                defaultSoundEditor.putInt("soundId", currentNotificationId);
                // persist the changes
                defaultSoundEditor.commit();

                // Show notification to the user
                Toast.makeText(getApplicationContext(), "Sound Changed !", Toast.LENGTH_SHORT).show();
            }
        });


    }

}