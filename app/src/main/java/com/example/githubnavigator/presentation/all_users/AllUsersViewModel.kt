package com.example.githubnavigator.presentation.all_users

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AllUsersViewModel : ViewModel() {

    private val _text = MutableStateFlow("").apply {
        value = "This is All Users Fragment"
    }
    val text: StateFlow<String> = _text
}