package com.example.githubnavigator.presentation.profile

import androidx.lifecycle.ViewModel
import com.example.githubnavigator.domain.profile.UserLogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userLogoutUseCase: UserLogoutUseCase
) : ViewModel() {

    suspend fun logout(){
        userLogoutUseCase.invoke()
    }


}