package com.example.githubnavigator.domain.userDetails

import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val userRepository: UserDetailsRepository
) {
    suspend operator fun invoke(username: String): List<UserRepoDomainEntity> {
        return userRepository.getUserRepos(username)
    }
}