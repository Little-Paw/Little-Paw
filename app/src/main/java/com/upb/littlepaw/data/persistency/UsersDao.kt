package com.upb.littlepaw.data.persistency

import androidx.room.*
import com.upb.littlepaw.homescreen.profile.models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE email =:email AND password =:password")
    fun getUser(email: String, password: String): Flow<List<UserEntity>>

    @Update
    fun updateUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)


}