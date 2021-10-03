package com.example.android_task4

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_task4.databinding.ActivityAddUpgradeBinding

class SettingsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(android.R.id.content,SettingsFagment()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /*override fun onBackPressed() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }*/
}