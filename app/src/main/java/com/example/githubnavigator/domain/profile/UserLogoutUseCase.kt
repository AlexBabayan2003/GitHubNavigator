package com.example.githubnavigator.domain.profile

import com.example.githubnavigator.domain.login.UserRepository
import javax.inject.Inject

class UserLogoutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() {
        return userRepository.logout()
    }
}