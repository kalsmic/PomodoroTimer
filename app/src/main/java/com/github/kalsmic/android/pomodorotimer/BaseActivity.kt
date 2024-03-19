package com.github.kalsmic.android.pomodorotimer;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        Intent goToMenuOption = new Intent();
        switch (item.getItemId()) {

            case R.id.menu_title_sounds:
                goToMenuOption.setClass(this, NotificationsActivity.class);
                startActivity(goToMenuOption);
                return true;

            case R.id.menuAbout:
                goToMenuOption.setClass(this, AboutActivity.class);
                startActivity(goToMenuOption);
                return true;

            case R.id.menuShare:
                goToMenuOption.setClass(this, ShareActivity.class);
                startActivity(goToMenuOption);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}