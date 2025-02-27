package com.example.userDetails

import com.example.domain.userDetails.UserRepoDomainEntity

object UserRepoMapper {
    fun fromResponse(response: UserRepoResponse): UserRepoDomainEntity {
        return UserRepoDomainEntity(
            id = response.id,
            name = response.name,
        )
    }
}