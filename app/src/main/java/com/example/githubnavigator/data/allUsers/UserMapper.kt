package com.example.githubnavigator.data.allUsers

import com.example.githubnavigator.data.remote.UserResponse
import com.example.githubnavigator.domain.allUsers.UserResponseDomain

object UserMapper {


    fun fromDomain(userResponseDomain: UserResponseDomain): AllUsersRoomEntity {
        return AllUsersRoomEntity(
            username = userResponseDomain.username,
            id = userResponseDomain.id,
            avatarUrl = userResponseDomain.avatarUrl,
        )
    }
fun toDomain(userEntity: AllUsersRoomEntity): UserResponseDomain {
    return UserResponseDomain(
        username = userEntity.username,
        id = userEntity.id,
        avatarUrl = userEntity.avatarUrl,
    )
}
}