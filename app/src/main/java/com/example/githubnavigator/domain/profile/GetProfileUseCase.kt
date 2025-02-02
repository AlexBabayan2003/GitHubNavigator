package com.example.githubnavigator.domain.profile

import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(username: String): ProfileEntity? {
        return profileRepository.getProfile(username)
    }
}