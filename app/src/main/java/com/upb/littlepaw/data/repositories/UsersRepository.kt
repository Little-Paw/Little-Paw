package com.upb.littlepaw.data.repositories

import android.content.Context
import com.upb.littlepaw.data.persistency.AuthPersistency
import com.upb.littlepaw.data.persistency.AppRoomDatabase
import com.upb.littlepaw.data.persistency.RoomPersistency

import com.upb.littlepaw.homescreen.profile.models.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class UsersRepository(val roomPersistency: RoomPersistency, val authPersistency: AuthPersistency) {

    suspend fun registerUser(user: UserEntity) {
        roomPersistency.db.UsersDao().insertUser(user)
        authPersistency.saveUser(user)

    }

    suspend fun updateUser(user:UserEntity) {
        roomPersistency.db.UsersDao().updateUser(user)
        authPersistency.saveUser(user)
    }

    fun login(email: String, password: String): Flow<UserEntity> {
        return roomPersistency.db.UsersDao().getUser(email, password).map {
            it.first()
        }.onEach { user ->
            authPersistency.saveUser(user)
            println(authPersistency.getUser()?.email)
        }
    }

    fun logout():Flow<Unit> {
        return flow { emit(authPersistency.deleteUser()) }
    }

    suspend fun isLoggedIn(): Boolean {
        return authPersistency.getUser() != null
    }

     suspend fun getLoggedUser(): UserEntity? {
        return authPersistency.getUser()
    }

}