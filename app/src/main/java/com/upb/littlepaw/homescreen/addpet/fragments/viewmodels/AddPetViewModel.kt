package com.upb.littlepaw.homescreen.addpet.fragments.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.homescreen.addpet.data.TemporalDb
import com.upb.littlepaw.homescreen.addpet.models.Pet

class AddPetViewModel: ViewModel() {
    init {
        println("AddPetView Model inicializado!")
    }

    val pet = MutableLiveData(Pet("","","","",""))
    fun createPet(pet:Pet){
        TemporalDb.savePet(pet)
    }
}