package com.upb.littlepaw.homescreen.addpet.models

import android.graphics.Bitmap
import android.net.Uri
import androidx.databinding.BaseObservable
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType

data class Pet (var name: String,var age: Int,var breed: String,var description: String,var gender: PetGender, var type: PetType, var image: ByteArray, var location: String)

