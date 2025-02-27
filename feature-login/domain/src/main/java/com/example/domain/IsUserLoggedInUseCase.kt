package com.example.domain

import javax.inject.Inject


class IsUserLoggedInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Boolean {
        return userRepository.isUserLoggedIn()
    }
}