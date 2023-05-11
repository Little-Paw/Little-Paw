package com.upb.littlepaw.data.api

import com.upb.littlepaw.BuildConfig
import com.upb.littlepaw.homescreen.addpet.models.Pet
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import kotlinx.coroutines.flow.Flow
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class AddPetResponse(val message: String)
interface Api {
    @GET("pets/list")
    fun getPetsList(): Flow<List<PetCard>>

    @POST("pets/add")
    fun addPet(@Body pet: Pet): Flow<Response<AddPetResponse>>
}

class ApiClient {
    val backendUrl = BuildConfig.BACKEND_URL

    val retrofit = Retrofit.Builder()
        .baseUrl(backendUrl)
        .addCallAdapterFactory(FlowCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val client = retrofit.create(Api::class.java)

    fun getPetsList(): Flow<List<PetCard>> {
        return client.getPetsList()
    }

    fun addPet(pet: Pet): Flow<Response<AddPetResponse>> {
        return client.addPet(pet)
    }

}