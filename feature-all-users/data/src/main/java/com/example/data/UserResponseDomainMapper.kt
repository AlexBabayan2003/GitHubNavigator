package com.example.data

import com.example.core.data.remote.UserResponse
import com.example.domain.User
import javax.inject.Inject

class UserResponseDomainMapper @Inject constructor() {
    fun fromResponse(userResponse: UserResponse): User {
        return User(
            username = userResponse.username,
            id = userResponse.id,
            avatarUrl = userResponse.avatarUrl,
        )
    }
}