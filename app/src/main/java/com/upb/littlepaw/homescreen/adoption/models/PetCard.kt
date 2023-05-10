package com.upb.littlepaw.homescreen.adoption.models

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

enum class PetType {
    DOG,
    CAT,
    BUNNY,
    BIRD,
    REPTILE,
    OTHER
}

data class PetTypeIcon(@DrawableRes val icon: Int, val type: PetType, val name: String)

enum class PetGender {
    MALE,
    FEMALE
}

@Entity
data class PetCard(@PrimaryKey val id:String, val name: String, var age: Int, val breed: String, val type: PetType, val gender: PetGender, val image: String, val distanceMeters: Int) :
    Serializable