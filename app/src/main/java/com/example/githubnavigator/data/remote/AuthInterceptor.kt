package com.example.githubnavigator.data.remote

import com.example.githubnavigator.data.login.UserPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import android.util.Base64

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
