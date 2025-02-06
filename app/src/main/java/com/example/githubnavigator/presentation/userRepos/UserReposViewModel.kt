package com.example.githubnavigator.presentation.userRepos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubnavigator.domain.userRepos.GetUserReposUseCase
import com.example.githubnavigator.domain.userRepos.UserReposDomainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {

    private val _reposState = MutableStateFlow<List<UserReposDomainEntity>>(emptyList())
    val reposState: StateFlow<List<UserReposDomainEntity>> = _reposState

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasMoreData = MutableStateFlow(true)
    val hasMoreData: StateFlow<Boolean> = _hasMoreData

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    private var _currentPage = 1
    private val _perPage = 10
    private var _isError = false

    init {
        loadRepos()
    }

    fun loadRepos() {
        if (_isLoading.value || !_hasMoreData.value || _isError) return
        _errorState.value = null
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val repos = getUserReposUseCase(_currentPage, _perPage)
                _reposState.value = _reposState.value + repos
                _hasMoreData.value = repos.isNotEmpty()
                _currentPage++
            } catch (e: Exception) {
                Log.e("UserReposViewModel", "Error loading repos: ${e.message}")
                _errorState.value = "Failed to load repositories: ${e.message}"
                _hasMoreData.value = false
                _isError = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}