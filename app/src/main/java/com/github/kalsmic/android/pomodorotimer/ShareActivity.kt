package com.github.kalsmic.android.pomodorotimer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.kalsmic.android.pomodorotimer.databinding.ActivityShareBinding;

import java.util.Objects;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShareBinding binding = ActivityShareBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // set back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Share");

        Button sendMessageButton = binding.buttonSend;

        // retrieve the message used in the edit text
        String message = (binding.editTextTextMultiLineMessage).getText().toString();

        sendMessageButton.setOnClickListener(v -> {
            // create destination with empty number so that the user can specify their own
            Uri destination = Uri.parse("smsto:");

            // create implicit intent ot any app with SENDTO capability
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO, destination);

            // pass the composed message to the messaging activity
            smsIntent.putExtra("sms_body", message);

            // launch the intent
            startActivity(smsIntent);

        });

    }

}