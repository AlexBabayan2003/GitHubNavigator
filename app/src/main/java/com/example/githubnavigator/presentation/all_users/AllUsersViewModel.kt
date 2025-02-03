package com.example.githubnavigator.presentation.all_users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubnavigator.domain.allUsers.UserEntityDomain

import com.example.githubnavigator.domain.allusers.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _users = MutableLiveData<List<UserEntityDomain>>(emptyList())
    val users: LiveData<List<UserEntityDomain>> = _users

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _lastUserId = MutableLiveData(0)
    val lastUserId: LiveData<Int> = _lastUserId

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            val newUsers = getAllUsersUseCase(_lastUserId.value ?: 0)
            _users.value = (_users.value ?: emptyList()) + newUsers
            _lastUserId.value = newUsers.lastOrNull()?.id ?: _lastUserId.value
            _isLoading.value = false
        }
    }
}