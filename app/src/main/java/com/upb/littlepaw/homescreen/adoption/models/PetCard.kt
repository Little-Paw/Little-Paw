package com.upb.littlepaw.homescreen.adoption.models

import androidx.annotation.DrawableRes
import java.io.Serializable

enum class PetType {
    DOG,
    CAT,
    OTHER
}

data class PetTypeIcon(@DrawableRes val icon: Int, val type: PetType, val name: String)

enum class PetGender {
    MALE,
    FEMALE
}

data class PetCard(val name: String, val age: Int, val breed: String, val type: PetType, val gender: PetGender, @DrawableRes val image: Int, val distanceMeters: Int) :
    Serializable