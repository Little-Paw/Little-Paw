package com.upb.littlepaw.homescreen.profile.fragments.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.homescreen.profile.models.User

class ProfileViewModel: ViewModel() {
    val user = MutableLiveData<User>(User("", "", "", ""))
}