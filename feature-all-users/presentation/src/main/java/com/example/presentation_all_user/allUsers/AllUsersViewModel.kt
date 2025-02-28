package com.example.presentation_all_user.allUsers

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetAllUsersUseCase
import com.example.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase,
) : ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLastPage = MutableStateFlow(false)
    val isLastPage: StateFlow<Boolean> = _isLastPage

    private var lastLoadedUserId: Int? = null
    private val pageSize = 20

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState = _errorState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = getAllUsersUseCase(lastLoadedUserId ?: 0)
            result.fold(
                onSuccess = { newUsers ->
                    _users.value = newUsers
                    lastLoadedUserId = newUsers.lastOrNull()?.id
                    _isLastPage.value = newUsers.size < pageSize
                },
                onFailure = { error ->
                    _errorState.value = error.message
                }
            )
            _isLoading.value = false
        }
    }

    fun loadMore() {
        if (_isLoading.value || _isLastPage.value) return
        viewModelScope.launch {
            _isLoading.value = true
            val result = getAllUsersUseCase(lastLoadedUserId ?: 0)
            result.fold(
                onSuccess = { newUsers ->
                    _users.value += newUsers
                    lastLoadedUserId = newUsers.lastOrNull()?.id
                    _isLastPage.value = newUsers.size < pageSize
                },
                onFailure = { error ->
                    Log.e("AllUsersViewModel", "Error loading more users: ${error.message}")
                    // Optionally, update an error state here.
                }
            )
            _isLoading.value = false
        }
    }
}
