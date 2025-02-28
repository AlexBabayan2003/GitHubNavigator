package com.example.domain

import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(profile: Profile) {
        profileRepository.updateProfile(profile)
    }
}