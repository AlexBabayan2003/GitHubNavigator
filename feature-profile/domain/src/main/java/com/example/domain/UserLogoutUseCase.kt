package com.example.domain

import com.example.domain.UserRepository
import javax.inject.Inject

class UserLogoutUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke() {
        return userRepository.logout()
    }
}