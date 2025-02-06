package com.example.githubnavigator.data.login

import android.content.SharedPreferences
import javax.inject.Inject

class UserPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun saveCredentials(username: String, token: String) {
        sharedPreferences.edit()
            .putString("username_key", username)
            .putString("token_key", token)
            .apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("username_key", null)
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token_key", null)
    }

    fun clearCredentials() {
        sharedPreferences.edit().clear().apply()
    }
}


