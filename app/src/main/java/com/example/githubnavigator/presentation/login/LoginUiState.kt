package com.example.githubnavigator.presentation.login

sealed class LoginUiState {
    data object Loading : LoginUiState()
    data object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}