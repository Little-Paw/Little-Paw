package com.upb.littlepaw.homescreen.adoption

import androidx.lifecycle.ViewModel
import com.upb.littlepaw.homescreen.adoption.models.PetCard

class AdoptionViewModel : ViewModel() {
    var locationCity: String? = null
    var locationCountry: String? = null
    var pets: List<PetCard> = listOf()
}