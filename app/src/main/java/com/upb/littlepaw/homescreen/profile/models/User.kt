package com.upb.littlepaw.homescreen.profile.models

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Entity
import androidx.room.PrimaryKey


class User(var name: MutableLiveData<String>, var email:MutableLiveData<String>, var password:MutableLiveData<String>, var country:MutableLiveData<String?>?)

@Entity
class UserEntity(
    var name: String,
    @PrimaryKey
    var email: String,
    var password: String,
    var country: String) {
    constructor(user:User):this(user.name.value!!, user.email.value!!, user.password.value!!, user.country?.value!!)
}