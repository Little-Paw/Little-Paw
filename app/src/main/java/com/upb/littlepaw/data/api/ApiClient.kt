package com.upb.littlepaw.data.api

import com.upb.littlepaw.BuildConfig
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import kotlinx.coroutines.flow.Flow
import me.sianaki.flowretrofitadapter.FlowCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Api {
    @GET("pets/list")
    fun getPetsList(): Flow<List<PetCard>>
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

}