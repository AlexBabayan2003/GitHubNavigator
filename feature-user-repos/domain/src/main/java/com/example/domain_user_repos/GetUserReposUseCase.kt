package com.example.domain_user_repos

import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val userReposRepository: UserReposRepository
) {
    suspend operator fun invoke(page: Int, perPage: Int): Result<List<UserRepos>> {
        return userReposRepository.getUserRepos(page, perPage)
    }
}