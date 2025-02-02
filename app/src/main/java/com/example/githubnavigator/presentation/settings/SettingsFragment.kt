package com.example.githubnavigator.presentation.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.githubnavigator.R

class SettingsFragment : Fragment() {

    private lateinit var switchDarkMode: Switch
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        private const val THEME_PREFS = "theme_prefs"
        private const val KEY_IS_DARK = "is_dark"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        switchDarkMode = root.findViewById(R.id.switchDarkMode)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)

        // Узнаём, что сохранено в prefs
        val isDark = sharedPreferences.getBoolean(KEY_IS_DARK, false)
        switchDarkMode.isChecked = isDark

        // Ставим слушатель переключения
        switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            // Запоминаем в prefs
            sharedPreferences.edit().putBoolean(KEY_IS_DARK, isChecked).apply()

            // Применяем тему
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
