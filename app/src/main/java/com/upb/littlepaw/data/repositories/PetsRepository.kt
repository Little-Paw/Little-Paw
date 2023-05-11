package com.upb.littlepaw.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import com.upb.littlepaw.data.api.AddPetResponse
import com.upb.littlepaw.data.api.ApiClient
import com.upb.littlepaw.data.persistency.AppRoomDatabase
import com.upb.littlepaw.data.persistency.RoomPersistency
import com.upb.littlepaw.homescreen.addpet.models.Pet
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Response

class PetsRepository(val apiClient: ApiClient, val roomPersistency: RoomPersistency, val context:Context) {

    fun getPetsList():Flow<List<PetCard>> {
        return roomPersistency.db.PetsDao().getPets()
    }

    suspend fun updatePetsList() {
        val previusPets = roomPersistency.db.PetsDao().getPets().first()
        if(isNetworkAvailable(context)) {
            val petsList = apiClient.getPetsList().first()
            println(petsList);
            if (petsList.isNotEmpty() && isThereChanges(previusPets, petsList)) {
                roomPersistency.db.PetsDao().deletePets()
                roomPersistency.db.PetsDao().savePets(petsList)
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

    suspend fun addPet(pet: Pet): Response<AddPetResponse> {
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