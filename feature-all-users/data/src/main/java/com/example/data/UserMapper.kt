package com.example.data

import com.example.database_all_users.AllUsersRoomEntity
import com.example.domain.UserResponseDomain

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