package com.github.kalsmic.android.pomodorotimer

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.github.kalsmic.android.pomodorotimer.databinding.ActivityAboutBinding
import java.util.Objects

class AboutActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var spinner: Spinner
    var aboutChoiceId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "About"

        // retrieve references to the Views defined in the activity_about.xml
        binding.webViewAbout.also { this.webView = it }

        spinner = binding.spinnerAboutUri

        // Create an ArrayAdapter using the string array and a default spinner layout
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.abouts_array, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        spinner.adapter = adapter


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                aboutChoiceId = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    /**
     * shows the selected web resource in webView.
     *
     * @param view the Go button
     */
    fun navigate(view: View?) {
        this.webView.visibility = View.VISIBLE
        this.webView.webViewClient = WebViewClient()
        val aboutURIOptions = resources.getStringArray(R.array.abouts_uri_array)
        this.webView.loadUrl(aboutURIOptions[aboutChoiceId])
    }
}