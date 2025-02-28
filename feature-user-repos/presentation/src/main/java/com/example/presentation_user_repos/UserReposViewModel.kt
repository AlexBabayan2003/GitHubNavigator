package com.example.presentation_user_repos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain_user_repos.GetUserReposUseCase
import com.example.domain_user_repos.UserRepos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {

    private val _reposState = MutableStateFlow<List<UserRepos>>(emptyList())
    val reposState: StateFlow<List<UserRepos>> = _reposState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasMoreData = MutableStateFlow(true)

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    private var _currentPage = 1
    private val _perPage = 10
    private var _isError = false

    init {
        loadRepos()
    }


    private fun loadRepos() {
        if (_isLoading.value || !_hasMoreData.value || _isError) return
        _errorState.value = null
        _isLoading.value = true
        viewModelScope.launch {
            getUserReposUseCase(_currentPage, _perPage)
                .onSuccess { repos ->
                    _reposState.value += repos
                    _hasMoreData.value = repos.isNotEmpty()
                    _currentPage++
                }
                .onFailure { error ->
                    Log.e("UserReposViewModel", "Error loading repos: ${error.message}")
                    _errorState.value = "Failed to load repositories: ${error.message}"
                    _hasMoreData.value = false
                    _isError = true
                }
            _isLoading.value = false
        }
    }
}
