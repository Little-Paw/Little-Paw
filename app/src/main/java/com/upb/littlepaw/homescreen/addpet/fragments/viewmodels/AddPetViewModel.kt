package com.upb.littlepaw.homescreen.addpet.fragments.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upb.littlepaw.data.repositories.PetsRepository
import com.upb.littlepaw.data.repositories.UsersRepository
import com.upb.littlepaw.homescreen.addpet.models.Pet
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType
import kotlinx.coroutines.launch

class AddPetViewModel(val petsRepository: PetsRepository, val usersRepository: UsersRepository): ViewModel() {

    val pet = MutableLiveData(Pet("", 0, "", "", PetGender.MALE, PetType.DOG, ByteArray(0)))
    val petTypes = MutableLiveData<List<PetType>>(listOf(PetType.DOG, PetType.CAT,PetType.BUNNY,PetType.BIRD, PetType.REPTILE, PetType.OTHER))

    init {
        println("AddPetView Model inicializado!")
    }

    fun onPetTypeSelected(item: PetType) {
        pet.value!!.type = item
    }
    fun changeGender (gender: PetGender){
        pet.value = pet.value!!.apply { this.gender = gender }
    }

    fun uploadPet(pet: Pet, onSuccess: () -> Unit, onError: () -> Unit) {
        viewModelScope.launch {
            try {
                pet.location = usersRepository.getLoggedUser()!!.country
                val response = petsRepository.addPet(pet)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }


}


