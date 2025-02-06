package com.example.githubnavigator.data.allUsers

import com.example.githubnavigator.data.remote.UserResponse
import com.example.githubnavigator.domain.allUsers.UserEntityDomain

object UserMapper {

    fun toDomain(roomEntity: AllUsersRoomEntity): UserEntityDomain {
        return UserEntityDomain(
            id = roomEntity.id,
            username = roomEntity.username,
            avatarUrl = roomEntity.avatarUrl
        )
    }

    fun fromDomain(userEntity: UserEntityDomain): AllUsersRoomEntity {
        return AllUsersRoomEntity(
            id = userEntity.id,
            username = userEntity.username,
            avatarUrl = userEntity.avatarUrl
        )
    }

    fun fromResponse(response: UserResponse): AllUsersRoomEntity {
        return AllUsersRoomEntity(
            id = response.id,
            username = response.username,
            avatarUrl = response.avatarUrl
        )
    }
}