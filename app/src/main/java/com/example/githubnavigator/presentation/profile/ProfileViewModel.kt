package com.example.githubnavigator.presentation.profile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {

    private val _text = MutableStateFlow("").apply {
        value = "This is Profile Fragment"
    }
    val text: StateFlow<String>
        get() = _text.asStateFlow()


}