package com.github.kalsmic.android.pomodorotimer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kalsmic.android.pomodorotimer.databinding.ActivityShareBinding
import java.util.Objects

class ShareActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShareBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Share"

        val sendMessageButton = binding.buttonSend

        // retrieve the message used in the edit text
        val message = binding.editTextTextMultiLineMessage.text.toString()

        sendMessageButton.setOnClickListener { v: View? ->
            // create destination with empty number so that the user can specify their own
            val destination = Uri.parse("smsto:")

            // create implicit intent ot any app with SENDTO capability
            val smsIntent = Intent(Intent.ACTION_SENDTO, destination)

            // pass the composed message to the messaging activity
            smsIntent.putExtra("sms_body", message)

            // launch the intent
            startActivity(smsIntent)
        }
    }
}