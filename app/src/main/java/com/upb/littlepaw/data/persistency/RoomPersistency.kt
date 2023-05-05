package com.upb.littlepaw.data.persistency

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType
import com.upb.littlepaw.homescreen.profile.models.User
import com.upb.littlepaw.homescreen.profile.models.UserEntity

@Database(entities = [PetCard::class, UserEntity::class], version = 1)
@TypeConverters(PetTypeConverters::class, PetGenderConverters::class)
abstract class RoomPersistency:RoomDatabase() {
    abstract fun PetsDao(): PetsDao
    abstract fun UsersDao(): UsersDao

    companion object {
        var instance: RoomPersistency? = null
        fun getInstance(context: Context): RoomPersistency {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, RoomPersistency::class.java, "RoomDb.db").build()
            }
            return instance!!
        }

    }
}

class PetTypeConverters {
    @TypeConverter
    fun petTypeToString(petType: PetType): String {
        return Gson().toJson(petType)
    }
    @TypeConverter
    fun stringToPetType(petTypeString: String): PetType {
        val type = object: TypeToken<PetType>() {}.type
        return Gson().fromJson(petTypeString, type)
    }
}

class PetGenderConverters {
    @TypeConverter
    fun petGenderToString(petGenre: PetGender): String {
        return Gson().toJson(petGenre)
    }
    @TypeConverter
    fun stringToPetGender(petGenreString: String): PetGender {
        val type = object: TypeToken<PetGender>() {}.type
        return Gson().fromJson(petGenreString, type)
    }
}

