package com.example.android_task4.presentation.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.android_task4.R

class SettingsFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }
}