package com.upb.littlepaw.homescreen.profile.models

import androidx.databinding.BaseObservable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class User(var name: MutableLiveData<String>, var email:MutableLiveData<String>, var password:String, var country:MutableLiveData<String?>?)

