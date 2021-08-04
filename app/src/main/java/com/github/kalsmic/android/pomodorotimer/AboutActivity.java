package com.github.kalsmic.android.pomodorotimer;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    WebView webView;
    Spinner spinner;
    int aboutChoiceId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // set back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About");

        // retrieve references to the Views defined in the activity_about.xml
        webView = (WebView) findViewById(R.id.webView_about);

        spinner = (Spinner) findViewById(R.id.spinner_about_uri);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.abouts_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aboutChoiceId = position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    /**
     * shows the selected web resource in webView.
     *
     * @param view the Go button
     */
    public void navigate(View view) {
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient());
        String[] aboutURIOptions = getResources().getStringArray(R.array.abouts_uri_array);
        webView.loadUrl(aboutURIOptions[aboutChoiceId]);
    }
}