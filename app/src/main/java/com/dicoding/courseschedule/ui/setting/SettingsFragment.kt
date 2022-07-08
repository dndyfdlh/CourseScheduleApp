package com.dicoding.courseschedule.ui.setting

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.notification.DailyReminder
import com.dicoding.courseschedule.util.ID_REPEATING
import com.dicoding.courseschedule.util.NightMode
import java.util.*

class SettingsFragment : PreferenceFragmentCompat()  {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        //TODO 10 : Update theme based on value in ListPreference
        val switchDarkMode: ListPreference? = findPreference(getString(R.string.pref_key_dark))
        switchDarkMode?.setOnPreferenceChangeListener{ preference, newValue ->
            newValue?.let {
                val modeSelected =
                    when((it as String).toUpperCase(Locale.ROOT)) {
                        NightMode.ON.name -> NightMode.ON
                        NightMode.OFF.name -> NightMode.OFF
                        else -> NightMode.AUTO
                    }
                updateTheme(modeSelected.value)
            }
            true
        }
        //TODO 11 : Schedule and cancel notification in DailyReminder based on SwitchPreference
        val switchNotification: SwitchPreference? = findPreference(getString(R.string.pref_key_notify))
        switchNotification?.setOnPreferenceChangeListener{ preference, newValue ->
            val broadcast = DailyReminder()
            if (newValue == true){
                broadcast.setDailyReminder(requireContext())
                Toast.makeText(activity,"enabled",Toast.LENGTH_LONG).show()
            }else{
                broadcast.cancelAlarm(requireContext())
                Toast.makeText(activity,"disabled",Toast.LENGTH_LONG).show()
            }

            true
        }
    }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}