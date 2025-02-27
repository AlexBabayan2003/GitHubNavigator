package com.example.presentation.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.example.presentation.profile.R

class SettingsFragment : Fragment() {

    private lateinit var switchDarkMode: SwitchCompat
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val THEME_PREFS = "theme_prefs"
        private const val KEY_IS_DARK = "is_dark"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        switchDarkMode = root.findViewById(R.id.switchDarkMode)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)

        val isDark = sharedPreferences.getBoolean(KEY_IS_DARK, false)
        switchDarkMode.isChecked = isDark

        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(KEY_IS_DARK, isChecked).apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
