package com.upb.littlepaw.homescreen.addpet.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.upb.littlepaw.homescreen.addpet.models.Pet

object TemporalDb {
    private var pet: MutableLiveData<Pet?> = MutableLiveData(null)

    fun savePet(newPet: Pet) {
        pet.value = newPet
    }
}