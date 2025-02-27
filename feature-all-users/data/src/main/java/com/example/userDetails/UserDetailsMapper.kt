package com.example.userDetails

import com.example.domain.userDetails.UserDetailsDomainEntity

object UserDetailsMapper {
    fun fromResponse(response: UserDetailsResponse): UserDetailsDomainEntity {
        return UserDetailsDomainEntity(
            username = response.username,
            avatarUrl = response.avatarUrl,
        )
    }
}