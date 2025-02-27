package com.example.githubnavigator

import androidx.lifecycle.ViewModel
import com.example.domain.IsUserLoggedInUseCase
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