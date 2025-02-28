package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Loading)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    fun login(username: String, token: String) {
        viewModelScope.launch {
            loginUseCase(username, token)
                .onSuccess {
                    _loginUiState.value = LoginUiState.Success
                }
                .onFailure { error ->
                    _loginUiState.value = LoginUiState.Error(error.message ?: "Unknown error")
                }
        }
    }
}
