package com.example.githubnavigator.domain.profile

import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(profile: ProfileDomainEntity) {
        profileRepository.updateProfile(profile)
    }
}