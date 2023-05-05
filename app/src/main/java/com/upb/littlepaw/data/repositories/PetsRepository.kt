package com.upb.littlepaw.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.upb.littlepaw.data.api.ApiClient
import com.upb.littlepaw.data.persistency.RoomPersistency
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PetsRepository {
    val apiClient = ApiClient()

    fun getPetsList(context:Context):Flow<List<PetCard>> {
        val roomPersistency = RoomPersistency.getInstance(context)
        return roomPersistency.PetsDao().getPets()
    }

    suspend fun updatePetsList(context:Context) {
        val roomPersistency = RoomPersistency.getInstance(context)
        if(isNetworkAvailable(context)) {
            val petsList = apiClient.getPetsList().first()
            roomPersistency.PetsDao().savePets(petsList)
        }
    }

    private fun isNetworkAvailable(context:Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return manager!!.activeNetworkInfo?.isConnected ?: false
    }
}