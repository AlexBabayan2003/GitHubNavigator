package com.example.userDetails

import com.example.domain.userDetails.UserRepo
import javax.inject.Inject

class UserRepoMapper @Inject constructor() {
    fun fromResponse(response: UserRepoResponse): UserRepo {
        return UserRepo(
            id = response.id,
            name = response.name,
        )
    }
}