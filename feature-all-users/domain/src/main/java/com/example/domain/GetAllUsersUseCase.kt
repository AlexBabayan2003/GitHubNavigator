package com.example.domain

import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val allUsersRepository: AllUsersRepository,
) {
    suspend operator fun invoke(since: Int): List<User> {
        return allUsersRepository.getAllUsers(since)
    }
}