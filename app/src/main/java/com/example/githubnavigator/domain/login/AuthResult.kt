package com.example.githubnavigator.domain.login

sealed class AuthResult {
    data class Success(val user: Any) : AuthResult()
    data class Error(val message: String) : AuthResult()
}
