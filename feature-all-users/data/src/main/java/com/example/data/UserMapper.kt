package com.example.data

import com.example.database_all_users.AllUsersRoomEntity
import com.example.domain.User
import javax.inject.Inject

class UserMapper @Inject constructor() {


    fun fromDomain(userResponseDomain: User): AllUsersRoomEntity {
        return AllUsersRoomEntity(
            username = userResponseDomain.username,
            id = userResponseDomain.id,
            avatarUrl = userResponseDomain.avatarUrl,
        )
    }
    fun toDomain(userEntity: AllUsersRoomEntity): User {
        return User(
            username = userEntity.username,
            id = userEntity.id,
            avatarUrl = userEntity.avatarUrl,
        )
    }
}