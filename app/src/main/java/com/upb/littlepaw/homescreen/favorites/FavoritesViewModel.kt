package com.upb.littlepaw.homescreen.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.R
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType

class FavoritesViewModel : ViewModel() {
    val petCardFavsList: MutableLiveData<List<PetCard>> = MutableLiveData()

    init {
        populatePetCardFavList()
    }
    fun setPetCardFavsList(petCardFavsList: List<PetCard>) {
        this.petCardFavsList.value = petCardFavsList
    }

    fun getPetCardFavsList(): List<PetCard> {
        return petCardFavsList.value ?: listOf()
    }

    fun populatePetCardFavList(){
        setPetCardFavsList(listOf(
            PetCard(name = "Buddy", age = 3, breed = "Labrador Retriever", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 500),
            PetCard(name = "Luna", age = 2, breed = "Siamese", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 250),
            PetCard(name = "Milo", age = 5, breed = "Maine Coon", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 300),
            PetCard(name = "Coco", age = 2, breed = "Rabbit", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 200),
            PetCard(name = "Leo", age = 3, breed = "Sphynx", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 100),
            PetCard(name = "Simba", age = 2, breed = "Bengal", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 900),
            PetCard(name = "Rocky", age = 2, breed = "Pug", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 800),
            PetCard(name = "Bella", age = 1, breed = "Scottish Fold", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 300),
            PetCard(name = "Cooper", age = 5, breed = "Labradoodle", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 600),
            PetCard(name = "Oscar", age = 2, breed = "Maine Coon", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 200),
            PetCard(name = "Coco", age = 1, breed = "Rabbit", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 230),
            PetCard(name = "Perico", age = 1, breed = "Paloma", type = PetType.BIRD, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 10)
        ))
    }
}