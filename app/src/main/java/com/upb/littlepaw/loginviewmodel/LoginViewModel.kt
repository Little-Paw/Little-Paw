package com.upb.littlepaw.loginviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email : MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")

    fun validate(): Boolean {
        return !email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
    }

    fun login(email: String, password: String, onSuccess: () -> Unit, onError: (error: String) -> Unit) {
        if (email == "juan@gmail.com" && password == "123") {
            onSuccess.invoke()
        } else {
            onError.invoke("Email or password incorrect.")
        }
    }
}