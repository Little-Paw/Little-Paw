package com.upb.littlepaw.data.persistency

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.upb.littlepaw.homescreen.profile.models.UserEntity
import kotlinx.coroutines.flow.first

class AuthPersistency(val context:Context) {
    val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "auth")
    val tokenKey = stringPreferencesKey("token")

    suspend fun saveUser(user:UserEntity) {
        context.dataStore.edit { preferences ->
            val gsonUser = Gson().toJson(user)
            preferences[tokenKey] = gsonUser
        }
    }

    suspend fun getUser(): UserEntity? {
        val type = object:TypeToken<UserEntity>() {}.type
        return Gson().fromJson(context.dataStore.data.first()[tokenKey], type)
    }

    suspend fun deleteUser() {
        context.dataStore.edit { preferences ->
            preferences.remove(tokenKey)
        }
    }
}
