package com.example.githubnavigator.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserRepositoriesViewModel : ViewModel() {

    private val _text = MutableStateFlow("").apply {
        value = "This is User Repositories Fragment"
    }
    val text: StateFlow<String> = _text
}