package com.example.githubnavigator.presentation.main

import androidx.lifecycle.ViewModel
import com.example.githubnavigator.domain.login.IsUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : ViewModel() {

    fun isUserLoggedIn(): Boolean {
        return isUserLoggedInUseCase()
    }
}