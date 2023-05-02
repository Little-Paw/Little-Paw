package com.upb.littlepaw.data.persistency

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType
import kotlinx.coroutines.flow.first

@Database(entities = [PetCard::class], version = 1)
@TypeConverters(PetTypeConverters::class, PetGenderConverters::class)
abstract class PetsPersistency:RoomDatabase() {
    abstract fun PetsDao(): PetsDao

    companion object {
        var instance: PetsPersistency? = null
        fun getInstance(context: Context): PetsPersistency {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, PetsPersistency::class.java, "PetsDb.db").build()
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