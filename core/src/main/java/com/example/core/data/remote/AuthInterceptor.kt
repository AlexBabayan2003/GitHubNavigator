package com.example.core.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import android.util.Base64
import com.example.core.data.local.UserPreferences

class AuthInterceptor @Inject constructor(
    private val userPreferences: UserPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val accessToken = userPreferences.getToken()
        if (accessToken != null) {
            requestBuilder.addHeader("Authorization", "Bearer $accessToken")
        }

        return chain.proceed(requestBuilder.build())
    }

}