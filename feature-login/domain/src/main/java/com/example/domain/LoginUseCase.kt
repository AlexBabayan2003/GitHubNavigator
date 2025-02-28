package com.example.domain

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, token: String): Result<Any> {
        return userRepository.login(username, token)
    }
}