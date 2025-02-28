package com.example.domain

import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(username: String): Profile? {
        return profileRepository.getProfile(username)
    }
}