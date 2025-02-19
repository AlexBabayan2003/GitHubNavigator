package com.example.githubnavigator.data.remote

import com.example.githubnavigator.domain.allUsers.UserResponseDomain

object UserResponseDomainMapper {
    fun fromResponse(userResponse: UserResponse): UserResponseDomain {
        return UserResponseDomain(
            username = userResponse.username,
            id = userResponse.id,
            avatarUrl = userResponse.avatarUrl,
//            fullName = userResponse.fullName,
//            bio = userResponse.bio
        )
    }
}