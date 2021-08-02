package com.github.kalsmic.android.pomodorotimer;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_title_sounds:
                Toast.makeText(this, "Menu Sounds Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menuAbout:
                Toast.makeText(this, "Menu About Clicked", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.menuShare:
                Toast.makeText(this, "Menu Share Clicked", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}