package com.upb.littlepaw.loginviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upb.littlepaw.data.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var email : MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")
    val usersRepository = UsersRepository()

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

    fun loginUser(context: Context,email:String, password:String, onSuccess: () -> Unit, onError: (error: String) -> Unit) {
        viewModelScope.launch {
            flow{
              val user = usersRepository.login(context,email,password).first()
              emit(user)
            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch { onError.invoke("Email or password incorrect.") }.collect()
        }
    }


}