package com.example.userDetails

import com.example.domain.userDetails.UserDetails
import javax.inject.Inject

class UserDetailsMapper @Inject constructor() {
    fun fromResponse(response: UserDetailsResponse): UserDetails {
        return UserDetails(
            username = response.username,
            avatarUrl = response.avatarUrl,
        )
    }
}