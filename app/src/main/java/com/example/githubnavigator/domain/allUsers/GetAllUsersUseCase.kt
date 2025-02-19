package com.example.githubnavigator.domain.allUsers

import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val allUsersRepository: AllUsersRepository,
) {
    suspend operator fun invoke(since: Int): List<UserResponseDomain> {
        return allUsersRepository.getAllUsers(since)
    }
}