package com.example.presentation_all_user.userDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.userDetails.GetUserDetailsUseCase
import com.example.domain.userDetails.GetUserReposUseCase
import com.example.domain.userDetails.UserDetails
import com.example.domain.userDetails.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserReposUseCase: GetUserReposUseCase,
) : ViewModel() {

    private val _userDetailsState = MutableStateFlow<UserDetails?>(null)
    val userDetailsState: StateFlow<UserDetails?> = _userDetailsState

    private val _userReposState = MutableStateFlow<List<UserRepo>>(emptyList())
    val userReposState: StateFlow<List<UserRepo>> = _userReposState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun loadUserDetails(username: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val userDetailsResult = getUserDetailsResult(username)
            val userReposResult = getUserReposResult(username)

            userDetailsResult.onSuccess { userDetails ->
                _userDetailsState.value = userDetails
            }
                .onFailure { e ->
                    _errorState.value = "Failed to load user details: ${e.message}"
                }

            userReposResult
                .onSuccess { userRepos ->
                    _userReposState.value = userRepos
                }
                .onFailure { e ->
                    _errorState.value = "Failed to load user repos: ${e.message}"
                }
            _isLoading.value = false
        }
    }

    private suspend fun getUserDetailsResult(username: String): Result<UserDetails> {
        return runCatching {
            getUserDetailsUseCase(username)
        }
    }

    private suspend fun getUserReposResult(username: String): Result<List<UserRepo>> {
        return runCatching {
            getUserReposUseCase(username)
        }
    }

}