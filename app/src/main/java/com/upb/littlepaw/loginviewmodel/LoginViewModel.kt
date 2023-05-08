package com.upb.littlepaw.loginviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upb.littlepaw.data.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(val usersRepository: UsersRepository) : ViewModel() {
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

    fun loginUser(email:String, password:String, onSuccess: () -> Unit, onError: (error: String) -> Unit) {
        viewModelScope.launch {
            flow{
              val user = usersRepository.login(email,password).first()
              emit(user)
            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch { onError.invoke("Email or password incorrect.") }.collect()
        }
    }


}