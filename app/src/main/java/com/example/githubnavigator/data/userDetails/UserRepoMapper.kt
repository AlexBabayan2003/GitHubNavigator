package com.example.githubnavigator.data.userDetails

import com.example.githubnavigator.domain.userDetails.UserRepoDomainEntity

object UserRepoMapper {
    fun fromResponse(response: UserRepoResponse): UserRepoDomainEntity {
        return UserRepoDomainEntity(
            id = response.id,
            name = response.name,
        )
    }
}