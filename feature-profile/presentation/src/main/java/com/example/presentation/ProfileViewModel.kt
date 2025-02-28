package com.example.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetProfileUseCase
import com.example.domain.Profile
import com.example.domain.UpdateProfileUseCase
import com.example.domain.UserLogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userLogoutUseCase: UserLogoutUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
) : ViewModel() {

    suspend fun logout() {
        userLogoutUseCase.invoke()
    }

    private val _profileState = MutableStateFlow<Profile?>(null)
    val profileState: StateFlow<Profile?> = _profileState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadProfile(username: String) {
        viewModelScope.launch {
            _isLoading.value = true

            _profileState.value = getProfileUseCase(username)
            _isLoading.value = false
        }
    }


    fun updateProfile(profile: Profile) {
        viewModelScope.launch {
            _isLoading.value = true
            updateProfileUseCase(profile)
            _profileState.value = profile
            _isLoading.value = false
        }
    }
}
