package com.example.githubnavigator.domain.allusers

import com.example.githubnavigator.domain.allUsers.AllUsersRepository
import com.example.githubnavigator.domain.allUsers.UserEntityDomain
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val allUsersRepository: AllUsersRepository
) {
    suspend operator fun invoke(since: Int): List<UserEntityDomain> {
        return allUsersRepository.getAllUsers(since)
    }
}