package com.example.domain.userDetails

import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val userRepository: UserDetailsRepository
) {
    suspend operator fun invoke(username: String): List<UserRepo> {
        return userRepository.getUserRepos(username)
    }
}