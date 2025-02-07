package com.example.githubnavigator.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubnavigator.domain.profile.GetProfileUseCase
import com.example.githubnavigator.domain.profile.ProfileDomainEntity
import com.example.githubnavigator.domain.profile.UpdateProfileUseCase
import com.example.githubnavigator.domain.profile.UserLogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userLogoutUseCase: UserLogoutUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase
) : ViewModel() {

    suspend fun logout() {
        userLogoutUseCase.invoke()
    }

    private val _profileState = MutableStateFlow<ProfileDomainEntity?>(null)
    val profileState: StateFlow<ProfileDomainEntity?> = _profileState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun loadProfile(username: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val profile = withContext(Dispatchers.IO) {
                getProfileUseCase(username)
            }
            _profileState.value = profile
            _isLoading.value = false
        }
    }


    fun updateProfile(profile: ProfileDomainEntity) {
        viewModelScope.launch {
            _isLoading.value = true
            updateProfileUseCase(profile)
            _profileState.value = profile
            _isLoading.value = false
        }
    }
}
