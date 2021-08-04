package com.github.kalsmic.android.pomodorotimer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        // set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Share");

        Button sendMessageButton = (Button) findViewById(R.id.button_send);

        // retrieve the message used in the edit text
        String message = (
                (EditText) findViewById(R.id.editTextTextMultiLine_message)
        ).getText().toString();

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create destination with empty number so that the user can specify their own
                Uri destination = Uri.parse("smsto:");

                // create implicit intent ot any app with SENDTO capability
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, destination);

                // pass the composed message to the messaging activity
                smsIntent.putExtra("sms_body", message);

                // launch the intent
                startActivity(smsIntent);

            }
        });

    }

}