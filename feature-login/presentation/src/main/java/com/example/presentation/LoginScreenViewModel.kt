package com.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.AuthResult
import com.example.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

//    private val _loginUiState = MutableLiveData<LoginUiState>()
//    val loginUiState: LiveData<LoginUiState> get() = _loginUiState

    private val _loginUiState = MutableStateFlow<LoginUiState>(LoginUiState.Loading)
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()


    fun login(username: String, token: String) {
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
sealed class LoginEvent {
    data object NavigateToUserRepos : LoginEvent()
}