package com.example.githubnavigator.domain.login

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(username: String, token: String): AuthResult {
        return userRepository.login(username, token)
    }
}
