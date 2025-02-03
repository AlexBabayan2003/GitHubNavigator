package com.example.githubnavigator.data.remote

import com.example.githubnavigator.data.local.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import android.util.Base64

class AuthInterceptor @Inject constructor(
    private val userPreferences: UserPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val username = userPreferences.getUsername()?.trim()
        val token = userPreferences.getToken()?.trim()

        // For Basic Auth with user:token
        val creds = "$username:$token"
        val base64Creds = Base64.encodeToString(creds.toByteArray(), Base64.NO_WRAP)
        val authHeaderValue = "Basic $base64Creds"

        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", authHeaderValue)
            .build()
        return chain.proceed(newRequest)
    }


}
