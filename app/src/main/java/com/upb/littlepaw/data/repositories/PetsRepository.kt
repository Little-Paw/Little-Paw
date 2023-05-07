package com.upb.littlepaw.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.upb.littlepaw.data.api.AddPetResponse
import com.upb.littlepaw.data.api.ApiClient
import com.upb.littlepaw.data.persistency.RoomPersistency
import com.upb.littlepaw.homescreen.addpet.models.Pet
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Response

class PetsRepository {
    val apiClient = ApiClient()

    fun getPetsList(context:Context):Flow<List<PetCard>> {
        val roomPersistency = RoomPersistency.getInstance(context)
        return roomPersistency.PetsDao().getPets()
    }

    suspend fun updatePetsList(context:Context) {
        val roomPersistency = RoomPersistency.getInstance(context)
        val previusPets = roomPersistency.PetsDao().getPets().first()
        if(isNetworkAvailable(context)) {
            val petsList = apiClient.getPetsList().first()
            if (petsList.isNotEmpty() && isThereChanges(previusPets, petsList)) {
                roomPersistency.PetsDao().deletePets()
                roomPersistency.PetsDao().savePets(petsList)
            }
        }
    }

    fun isThereChanges(previusPetList: List<PetCard>, newPetList: List<PetCard>): Boolean {
        if(previusPetList.size != newPetList.size) return true
        for(i in previusPetList.indices) {
            if(previusPetList[i].id != newPetList[i].id) return true
        }
        return false
    }

    suspend fun addPet(context:Context, pet: Pet): Response<AddPetResponse> {
        if(isNetworkAvailable(context)) {
            return apiClient.addPet(pet).first()
        }else{
            throw Exception("There is no internet connection")
        }
    }

    private fun isNetworkAvailable(context:Context): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return manager!!.activeNetworkInfo?.isConnected ?: false
    }
}