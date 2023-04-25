package com.upb.littlepaw.homescreen.addpet.fragments.viewmodels

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.internal.ContextUtils.getActivity
import com.upb.littlepaw.homescreen.addpet.data.TemporalDb
import com.upb.littlepaw.homescreen.addpet.models.Pet
import com.upb.littlepaw.homescreen.adoption.models.PetGender

class AddPetViewModel: ViewModel() {
    private val _selectedImageUri = MutableLiveData<Uri?>()
    val selectedImageUri: MutableLiveData<Uri?>
        get() = _selectedImageUri


    init {
        println("AddPetView Model inicializado!")
    }

    val pet = MutableLiveData(Pet("","","","",PetGender.MALE))
    fun createPet(pet:Pet){
        TemporalDb.savePet(pet)
    }
    fun changeGender (gender: PetGender){
        pet.value = pet.value!!.apply { this.gender = gender }
    }


}


