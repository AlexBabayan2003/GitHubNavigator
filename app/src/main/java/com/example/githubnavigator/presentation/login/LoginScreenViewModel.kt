package com.example.githubnavigator.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubnavigator.domain.login.AuthResult
import com.example.githubnavigator.domain.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginUiState = MutableLiveData<LoginUiState>()
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState

    fun login(username: String, token: String) {
        _loginUiState.value = LoginUiState.Loading

        viewModelScope.launch {
            when (val result = loginUseCase(username, token)) {
                is AuthResult.Success -> {
                    _loginUiState.value = LoginUiState.Success
                }
                is AuthResult.Error -> {
                    _loginUiState.value = LoginUiState.Error(result.message)
                }
            }
        }
    }
}