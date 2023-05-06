package com.upb.littlepaw.data.repositories

import android.content.Context
import com.upb.littlepaw.data.persistency.AuthPersistency
import com.upb.littlepaw.data.persistency.RoomPersistency

import com.upb.littlepaw.homescreen.profile.models.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class UsersRepository {

    suspend fun registerUser(context: Context, user: UserEntity) {
        RoomPersistency.getInstance(context).UsersDao().insertUser(user)
        AuthPersistency.saveUser(context, user)

    }

    suspend fun updateUser(context:Context, user:UserEntity) {
        RoomPersistency.getInstance(context).UsersDao().updateUser(user)
        AuthPersistency.saveUser(context, user)
    }

    fun login(context: Context, email: String, password: String): Flow<UserEntity> {
        return RoomPersistency.getInstance(context).UsersDao().getUser(email, password).map {
            it.first()
        }.onEach { user ->
            AuthPersistency.saveUser(context, user)
            println(AuthPersistency.getUser(context)?.email)
        }
    }

    fun logout(context: Context):Flow<Unit> {
        return flow { emit(AuthPersistency.deleteUser(context)) }
    }

    suspend fun isLoggedIn(context: Context): Boolean {
        return AuthPersistency.getUser(context) != null
    }

     suspend fun getLoggedUser(context: Context): UserEntity? {
        return AuthPersistency.getUser(context)
    }

}