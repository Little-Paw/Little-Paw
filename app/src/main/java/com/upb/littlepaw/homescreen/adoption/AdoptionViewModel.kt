package com.upb.littlepaw.homescreen.adoption

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.R
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType

class AdoptionViewModel : ViewModel() {
//    var locationCity: String? = null
//    var locationCountry: String? = null

    private val _petCardsList = MutableLiveData<List<PetCard>>()
    val petCardsList: LiveData<List<PetCard>>
        get() = _petCardsList

    private val _selectedPetType = MutableLiveData<PetType>()
    val selectedPetType: LiveData<PetType>
        get() = _selectedPetType

    init {
        setSelectedPetType(PetType.DOG)
        populatePetCardsList()
    }
    fun setSelectedPetType(petType: PetType) {
        _selectedPetType.value = petType
    }

    fun setPetCardsList(petCardsList: List<PetCard>) {
        _petCardsList.value = petCardsList
    }

    fun populatePetCardsList(){
        setPetCardsList(
            listOf(
                PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 100),
                PetCard(name = "Buddy", age = 3, breed = "Labrador Retriever", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 500),
                PetCard(name = "Luna", age = 2, breed = "Siamese", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 250),
                PetCard(name = "Max", age = 1, breed = "Golden Retriever", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 1000),
                PetCard(name = "Charlie", age = 4, breed = "French Bulldog", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 750),
                PetCard(name = "Milo", age = 5, breed = "Maine Coon", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 300),
                PetCard(name = "Coco", age = 2, breed = "Rabbit", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 200),
                PetCard(name = "Rocky", age = 3, breed = "German Shepherd", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 800),
                PetCard(name = "Bella", age = 2, breed = "Persian", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 150),
                PetCard(name = "Oscar", age = 1, breed = "Dachshund", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 600),
                PetCard(name = "Lucy", age = 4, breed = "Beagle", type = PetType.DOG, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 400),
                PetCard(name = "Leo", age = 3, breed = "Sphynx", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 100),
                PetCard(name = "Simba", age = 2, breed = "Bengal", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 900),
                PetCard(name = "Teddy", age = 1, breed = "Labradoodle", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 550),
                PetCard(name = "Mia", age = 4, breed = "Ragdoll", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 350),
                PetCard(name = "Ruby", age = 2, breed = "Chihuahua", type = PetType.DOG, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 300),
                PetCard(name = "Oliver", age = 3, breed = "Russian Blue", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 200),
                PetCard(name = "Coco", age = 1, breed = "Holland Lop", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 150),
                PetCard(name = "Max", age = 4, breed = "Labrador Retriever", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 400),
                PetCard(name = "Sasha", age = 3, breed = "Siamese", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 250),
                PetCard(name = "Rocky", age = 2, breed = "Pug", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 800),
                PetCard(name = "Bella", age = 1, breed = "Scottish Fold", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 300),
                PetCard(name = "Cooper", age = 5, breed = "Labradoodle", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 600),
                PetCard(name = "Lucy", age = 4, breed = "Bulldog", type = PetType.DOG, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 400),
                PetCard(name = "Leo", age = 2, breed = "Devon Rex", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 100),
                PetCard(name = "Nala", age = 3, breed = "Birman", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 900),
                PetCard(name = "Zeus", age = 1, breed = "Great Dane", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 550),
                PetCard(name = "Lily", age = 4, breed = "Persian", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 350),
                PetCard(name = "Charlie", age = 3, breed = "Chihuahua", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 300),
                PetCard(name = "Oscar", age = 2, breed = "Maine Coon", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 200),
                PetCard(name = "Coco", age = 1, breed = "Rabbit", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 230),
                PetCard(name = "Perico", age = 1, breed = "Paloma", type = PetType.BIRD, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 10)
            )
        )
    }
    fun getFilteredPetCardsList(petType: PetType? = selectedPetType.value): List<PetCard> {
        return petCardsList.value?.filter { it.type == petType } ?: listOf()
    }

}