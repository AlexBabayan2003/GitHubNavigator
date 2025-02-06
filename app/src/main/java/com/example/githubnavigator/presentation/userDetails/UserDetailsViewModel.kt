package com.example.githubnavigator.presentation.userDetails

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubnavigator.domain.userDetails.GetUserDetailsUseCase
import com.example.githubnavigator.domain.userDetails.GetUserReposUseCase
import com.example.githubnavigator.domain.userDetails.UserDetailsDomainEntity
import com.example.githubnavigator.domain.userDetails.UserRepoDomainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {

    private val _userDetailsState = MutableStateFlow<UserDetailsDomainEntity?>(null)
    val userDetailsState: StateFlow<UserDetailsDomainEntity?> = _userDetailsState

    private val _userReposState = MutableStateFlow<List<UserRepoDomainEntity>>(emptyList())
    val userReposState: StateFlow<List<UserRepoDomainEntity>> = _userReposState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun loadUserDetails(username: String) {
        Log.d("UserDetailsViewModel", "loadUserDetails called for: $username")
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val userDetails = getUserDetailsUseCase(username)
                Log.d("UserDetailsViewModel", "User details loaded: $userDetails")
                _userDetailsState.value = userDetails
                val userRepos = getUserReposUseCase(username)
                Log.d("UserDetailsViewModel", "User repos loaded: $userRepos")
                _userReposState.value = userRepos
            } catch (e: Exception) {
                Log.e("UserDetailsViewModel", "Error loading user details: ${e.message}")
                _errorState.value = "Failed to load user details: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}