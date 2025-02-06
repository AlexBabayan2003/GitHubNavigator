package com.example.githubnavigator.domain.userRepos

import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val userReposRepository: UserReposRepository
) {
    suspend operator fun invoke(page: Int, perPage: Int): List<UserReposDomainEntity> {
        return userReposRepository.getUserRepos(page, perPage)
    }
}