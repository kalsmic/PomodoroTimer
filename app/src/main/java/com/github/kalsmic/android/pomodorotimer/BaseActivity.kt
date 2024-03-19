package com.github.kalsmic.android.pomodorotimer

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val goToMenuOption = Intent()
        when (item.itemId) {
            R.id.menu_title_sounds -> {
                goToMenuOption.setClass(this, NotificationsActivity::class.java)
                startActivity(goToMenuOption)
                return true
            }

            R.id.menuAbout -> {
                goToMenuOption.setClass(this, AboutActivity::class.java)
                startActivity(goToMenuOption)
                return true
            }

            R.id.menuShare -> {
                goToMenuOption.setClass(this, ShareActivity::class.java)
                startActivity(goToMenuOption)
                return super.onOptionsItemSelected(item)
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}