package com.upb.littlepaw.homescreen.adoption

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upb.littlepaw.R
import com.upb.littlepaw.data.repositories.PetsRepository
import com.upb.littlepaw.data.repositories.UsersRepository
import com.upb.littlepaw.homescreen.addpet.fragments.SavePetDialog
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class AdoptionViewModel(val petsRepository: PetsRepository, val usersRepository: UsersRepository) : ViewModel() {
//    var locationCity: String? = null
//    var locationCountry: String? = null

    private val petSearchListeners = mutableListOf<(Unit) -> Unit>()

    val petSearchQuery: MutableLiveData<String> = MutableLiveData()

    val petCardsList: MutableLiveData<List<PetCard>> = MutableLiveData()

    val selectedPetType: MutableLiveData<PetType> = MutableLiveData()

    init {
        setPetSearchQuery("")
        setSelectedPetType(PetType.DOG)
        //populatePetCardsList()
    }

    fun setOnNotifyPetCarListParamsChanged(listener: (Unit) -> Unit) {
        petSearchListeners.add(listener)
    }

    fun notifyPetCardListParamsChanged() {
        petSearchListeners.forEach { it.invoke(Unit) }
    }

    fun setPetSearchQuery(petSearchQuery: String) {
        this.petSearchQuery.value = petSearchQuery
    }
    fun setSelectedPetType(petType: PetType) {
        this.selectedPetType.value = petType
    }

    fun getSelectedPetType(): PetType {
        return selectedPetType.value ?: PetType.DOG
    }

    fun setPetCardsList(petCardsList: List<PetCard>) {
        this.petCardsList.value = petCardsList
    }

    fun getFilteredPetCardsList(): List<PetCard> {
        return petCardsList.value?.filter { it.type == selectedPetType.value && it.name.contains(
            petSearchQuery.value.toString(), true) } ?: listOf()
    }

    fun getPetsList(onError: () -> Unit) {
        viewModelScope.launch {
            petsRepository.getPetsList().catch { e ->
                onError()
                println(e.toString())
            }.flowOn(Dispatchers.IO).collect{
                println(it.toString())
                petCardsList.value = it
                getFilteredPetCardsList()

            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            petsRepository.updatePetsList()
        }

    }

    suspend fun isUserLoggedIn(): Boolean {
        return usersRepository.isLoggedIn()
    }

    fun logout(): Flow<Unit> {
        return usersRepository.logout()
    }

    private fun populatePetCardsList(){
        setPetCardsList(
            listOf(
                PetCard(id="wdjahdjdad54545","Doki", 15, "Husky", PetType.DOG, PetGender.MALE, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Golden_Retriever_standing_Tucker.jpg/220px-Golden_Retriever_standing_Tucker.jpg", "AU"),
                /*
                PetCard(name = "Buddy", age = 3, breed = "Labrador Retriever", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 500),
                PetCard(name = "Luna", age = 2, breed = "Siamese", type = PetType.CAT, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 250),
                PetCard(name = "Max", age = 1, breed = "Golden Retriever", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 1000),
                PetCard(name = "Charlie", age = 4, breed = "French Bulldog", type = PetType.DOG, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 750),
                PetCard(name = "Milo", age = 5, breed = "Maine Coon", type = PetType.CAT, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 300),
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
                PetCard(name = "Sirpancho", age = 1, breed = "Holland Lop", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 150),
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
                PetCard(name = "ByRabbit", age = 1, breed = "Rabbit", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 230),
                PetCard(name = "Coco", age = 2, breed = "Rabbit", type = PetType.BUNNY, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 200),
                PetCard(name = "Pixie", age = 1, breed = "Perico", type = PetType.BIRD, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 10),
                PetCard(name = "Pixie", age = 1, breed = "Perico", type = PetType.BIRD, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 10),
                PetCard(name = "Perico", age = 1, breed = "Paloma", type = PetType.BIRD, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 10),
                PetCard(name = "Pixie", age = 1, breed = "Perico", type = PetType.BIRD, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 10),
                PetCard(name = "Oliver", age = 3, breed = "Russian Blue", type = PetType.REPTILE, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 200),
                PetCard(name = "Coco", age = 1, breed = "Holland Lop", type = PetType.REPTILE, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 150),
                PetCard(name = "Max", age = 4, breed = "Labrador Retriever", type = PetType.REPTILE, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 400),
                PetCard(name = "Sasha", age = 3, breed = "Siamese", type = PetType.REPTILE, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 250),
                PetCard(name = "Lucy", age = 4, breed = "Beagle", type = PetType.OTHER, gender = PetGender.FEMALE, image = R.drawable.dog_placeholder, distanceMeters = 400),
                PetCard(name = "Leo", age = 3, breed = "Sphynx", type = PetType.OTHER, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 100),
                PetCard(name = "Simba", age = 2, breed = "Bengal", type = PetType.OTHER, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 900),
                PetCard(name = "Teddy", age = 1, breed = "Labradoodle", type = PetType.OTHER, gender = PetGender.MALE, image = R.drawable.dog_placeholder, distanceMeters = 550),
*/
            )
        )
    }

}