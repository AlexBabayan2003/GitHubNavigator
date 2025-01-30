package com.example.githubnavigator.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val _text = MutableStateFlow("").apply {
        value = "This is Profile Fragment"
    }
    val text: StateFlow<String> = _text
}