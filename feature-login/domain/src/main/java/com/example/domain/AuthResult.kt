package com.example.domain

sealed class AuthResult {
    data class Success(val user: Any) : AuthResult()
    data class Error(val message: String) : AuthResult()
}