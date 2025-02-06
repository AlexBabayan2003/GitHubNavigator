package com.example.githubnavigator.data.userDetails

import com.example.githubnavigator.domain.userDetails.UserDetailsDomainEntity

object UserDetailsMapper {
    fun fromResponse(response: UserDetailsResponse): UserDetailsDomainEntity {
        return UserDetailsDomainEntity(
            username = response.username,
            avatarUrl = response.avatarUrl,
        )
    }
}