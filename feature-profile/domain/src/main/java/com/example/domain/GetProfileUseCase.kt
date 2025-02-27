package com.example.domain

import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(username: String): ProfileDomainEntity? {
        return profileRepository.getProfile(username)
    }
}