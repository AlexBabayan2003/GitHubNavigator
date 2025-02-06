package com.example.githubnavigator.domain.userDetails

import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val userRepository: UserDetailsRepository
) {
    suspend operator fun invoke(username: String): UserDetailsDomainEntity {
        return userRepository.getUserDetails(username)
    }
}