package com.upb.littlepaw.homescreen.profile.fragments.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.upb.littlepaw.data.repositories.UsersRepository
import com.upb.littlepaw.homescreen.profile.models.User
import com.upb.littlepaw.homescreen.profile.models.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class ProfileViewModel(val usersRepository: UsersRepository): ViewModel() {
    val user = MutableLiveData<User>(User(MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String?>()))
    val errorFullName = MutableLiveData<String>()
    val errorEmail = MutableLiveData<String>()
    val touchedFullName = MutableLiveData<Boolean>()
    val touchedEmail = MutableLiveData<Boolean>()
    val buttonEnabled = MutableLiveData<Boolean>()

    init {
        setTouchedFullName(false)
        setTouchedEmail(false)
        setErrorFullName("")
        setName("")
        setEmail("")
        setErrorEmail("")
        setCountry("")
        setButtonEnabled(false)
    }

    suspend fun initializeUser(){
        val userEntity = usersRepository.getLoggedUser()
        if(userEntity != null) {
            setName(userEntity.name)
            setEmail(userEntity.email)
            setCountry(userEntity.country)
            setPassword(userEntity.password)
        }
    }

    fun updateUser(user: UserEntity, onSuccess: () -> Unit, onError:(error:String) -> Unit) {
        viewModelScope.launch{
            flow{
                usersRepository.updateUser(user)
                emit(user)
            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch { onError.invoke("An error has occurred") }.collect()
        }
    }


    fun setName(name:String) {
        this.user.value?.name?.value = name
    }

    fun setEmail(email:String) {
        this.user.value?.email?.value = email
    }

    fun setPassword(password:String) {
        this.user.value?.password?.value = password
    }

    fun setCountry(country:String) {
        this.user.value?.country?.value = country
    }

    fun setErrorFullName(errorFullName:String) {
        this.errorFullName.value = errorFullName
    }

    fun setErrorEmail(errorEmail:String) {
        this.errorEmail.value = errorEmail
    }


    fun setTouchedFullName(touchedFullName:Boolean) {
        this.touchedFullName.value = touchedFullName
    }

    fun setTouchedEmail(touchedEmail:Boolean) {
        this.touchedEmail.value = touchedEmail
    }

    fun setButtonEnabled(buttonEnabled:Boolean) {
        this.buttonEnabled.value = buttonEnabled
    }

    fun validateFullName():Boolean {
        val fullName = user.value?.name?.value.toString()
        val isValid = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*\$").matcher(fullName).matches()
        return if (isValid) {
            setErrorFullName("")
            true
        } else {
            if(touchedFullName.value!!) {
                setErrorFullName("Por favor ingrese un nombre válido")
            } else {
                setErrorFullName("")
            }
            false
        }
    }

    fun validateEmail():Boolean {
        val email = user.value?.email?.value.toString()
        val isValid = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$").matcher(email).matches()
        return if(isValid){
            setErrorEmail("")
            true
        } else {
            if(touchedEmail.value!!) {
                setErrorEmail("Por favor ingrese un correo válido")
            } else {
                setErrorEmail("")
            }
            false
        }

    }

    fun validateAll():Boolean {
        setButtonEnabled(validateFullName() && validateEmail() && user.value?.country?.value != null)
        return validateFullName() && validateEmail() && user.value?.country?.value != null
    }


}