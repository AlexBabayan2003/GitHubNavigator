package com.example.data

import com.example.core.data.remote.UserResponse
import com.example.domain.UserResponseDomain

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