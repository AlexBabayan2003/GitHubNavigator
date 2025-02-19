package com.example.githubnavigator.presentation.allUsers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubnavigator.domain.allUsers.GetAllUsersUseCase
import com.example.githubnavigator.domain.allUsers.UserResponseDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _users = MutableLiveData<List<UserResponseDomain>>(emptyList())
    val users: LiveData<List<UserResponseDomain>> = _users

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            val newUsers = getAllUsersUseCase(0)
            _users.value = newUsers
            _isLoading.value = false
        }
    }
}