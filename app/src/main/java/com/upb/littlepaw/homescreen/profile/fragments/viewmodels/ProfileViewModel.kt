package com.upb.littlepaw.homescreen.profile.fragments.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.data.repositories.UsersRepository
import com.upb.littlepaw.homescreen.profile.models.User
import java.util.regex.Pattern

class ProfileViewModel: ViewModel() {
    val user = MutableLiveData<User>(User(MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String?>()))
    val errorFullName = MutableLiveData<String>()
    val errorEmail = MutableLiveData<String>()
    val touchedFullName = MutableLiveData<Boolean>()
    val touchedEmail = MutableLiveData<Boolean>()
    val buttonEnabled = MutableLiveData<Boolean>()
    val usersRepository = UsersRepository()

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

    suspend fun initializeUser(context: Context){
        val userEntity = usersRepository.getLoggedUser(context)
        if(userEntity != null) {
            setName(userEntity.name)
            setEmail(userEntity.email)
            setCountry(userEntity.country)
        }
    }


    fun setName(name:String) {
        this.user.value?.name?.value = name
    }

    fun setEmail(email:String) {
        this.user.value?.email?.value = email
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