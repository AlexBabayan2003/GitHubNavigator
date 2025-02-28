package com.example.domain.userDetails

import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserDetailsRepository
) {
    suspend operator fun invoke(username: String): UserDetails {
        return userRepository.getUserDetails(username)
    }
}