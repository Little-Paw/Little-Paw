package com.upb.littlepaw.homescreen.addpet.models

import androidx.databinding.BaseObservable
import com.upb.littlepaw.homescreen.adoption.models.PetGender

data class Pet (var name: String,var birthday: String,var breed: String,var description: String,var gender: PetGender)
