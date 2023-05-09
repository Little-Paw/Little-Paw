package com.upb.littlepaw.register.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.upb.littlepaw.data.repositories.UsersRepository
import com.upb.littlepaw.homescreen.profile.models.User
import com.upb.littlepaw.homescreen.profile.models.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.regex.Pattern

class RegisterViewModel: ViewModel() {
    val user = MutableLiveData<User>(User(MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String?>()))
    val repeatPassword = MutableLiveData<String>()
    val touchedFullName = MutableLiveData<Boolean>()
    val touchedEmail = MutableLiveData<Boolean>()
    val touchedPassword = MutableLiveData<Boolean>()
    val touchedRepeatPassword = MutableLiveData<Boolean>()
    val buttonEnabled = MutableLiveData<Boolean>()
    val errorFullName = MutableLiveData<String>()
    val errorEmail = MutableLiveData<String>()
    val errorPassword = MutableLiveData<String>()
    val errorRepeatPassword = MutableLiveData<String>()
    val usersRepository = UsersRepository()

    private val auth = Firebase.auth

    init {
        setName("")
        setEmail("")
        setPassword("")
        setRepeatPassword("")
        setCountry("")
        setErrorFullName("")
        setErrorEmail("")
        setErrorPassword("")
        setErrorRepeatPassword("")
        setTouchedFullName(false)
        setTouchedEmail(false)
        setTouchedPassword(false)
        setTouchedRepeatPassword(false)
        setButtonEnabled(false)

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

    fun setRepeatPassword(repeatPassword:String) {
        this.repeatPassword.value = repeatPassword
    }

    fun setCountry(country:String) {
        this.user.value?.country?.value = country
    }

    fun setTouchedFullName(touchedFullName:Boolean) {
        this.touchedFullName.value = touchedFullName
    }

    fun setTouchedEmail(touchedEmail:Boolean) {
        this.touchedEmail.value = touchedEmail
    }

    fun setTouchedPassword(touchedPassword:Boolean) {
        this.touchedPassword.value = touchedPassword
    }

    fun setTouchedRepeatPassword(touchedRepeatPassword:Boolean) {
        this.touchedRepeatPassword.value = touchedRepeatPassword
    }

    fun setErrorFullName(errorFullName:String) {
        this.errorFullName.value = errorFullName
    }

    fun setErrorEmail(errorEmail:String) {
        this.errorEmail.value = errorEmail
    }

    fun setErrorPassword(errorPassword:String) {
        this.errorPassword.value = errorPassword
    }

    fun setErrorRepeatPassword(errorRepeatPassword:String) {
        this.errorRepeatPassword.value = errorRepeatPassword
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

    fun validateNewPassword(): Boolean {
        val password = user.value?.password?.value.toString()
        val has8Characters = password.length >= 8
        val has1UpperCase = password.any { it.isUpperCase() }
        val has1LowerCase = password.any { it.isLowerCase() }
        val has1Number = password.any { it.isDigit() }
        val has1SpecialCharacter = password.any { it.isLetterOrDigit().not() }

        return if (has8Characters && has1UpperCase && has1LowerCase && has1Number && has1SpecialCharacter) {
            setErrorPassword("")
            true
        } else {
            if(touchedPassword.value!!) {
                setErrorPassword("The password must have: \n" +
                        (if (has8Characters) "" else "- 8 or more characters \n") +
                        (if (has1UpperCase) "" else "- 1 uppercase letter \n") +
                        (if (has1LowerCase) "" else "- 1 lowercase letter \n") +
                        (if (has1Number) "" else "- 1 number \n") +
                        (if (has1SpecialCharacter) "" else "- 1 special character"))
            } else {
                setErrorPassword("")
            }
            false
        }
    }

    fun validateRepeatNewPassword(): Boolean {
        val password = user.value?.password?.value.toString()
        val repeatPassword = repeatPassword.value.toString()
        return if (password == repeatPassword) {
            setErrorRepeatPassword("")
            true
        } else {
            if(touchedRepeatPassword.value!!) {
                setErrorRepeatPassword("The passwords do not match")
            } else {
                setErrorRepeatPassword("")
            }
            false
        }
    }

    fun validateAll():Boolean {
        setButtonEnabled(validateFullName() && validateEmail() && validateNewPassword() && validateRepeatNewPassword() && user.value?.country?.value != null)
        return validateFullName() && validateEmail() && validateNewPassword() && validateRepeatNewPassword() && user.value?.country?.value != null
    }

//    fun createUser(context: Context, user: UserEntity, onSuccess: () -> Unit, onError: () -> Unit) {
//        viewModelScope.launch {
//            flow {
//                usersRepository.registerUser(context, user)
//                emit(user)
//            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch {
//                it.printStackTrace()
//                onError()
//            }.collect()
//        }
//    }

//    fun createUser(context: Context, user: UserEntity, onSuccess: () -> Unit, onError: (String) -> Unit) {
//        val email = user.email
//        val password = user.password
//
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Registro exitoso
//                    onSuccess()
//                } else {
//                    val errorMessage = when (val exception = task.exception) {
//                        is FirebaseAuthInvalidCredentialsException -> "Credenciales inválidas"
//                        is FirebaseAuthUserCollisionException -> "El usuario ya está registrado"
//                        is FirebaseException -> "Error de Firebase: ${exception.message}"
//                        else -> "Error desconocido: ${exception?.message}"
//                    }
//                    onError(errorMessage)
//                }
//            }
//    }

    fun createUser(
        context: Context,
        user: UserEntity,
        onSuccess: () -> Unit,
        onError: (error: String) -> Unit
    ) {
        viewModelScope.launch {
            flow {
                val email = user.email
                val password = user.password

                auth.createUserWithEmailAndPassword(email, password).await()
                usersRepository.registerUser(context, user)
                emit(user)
            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch {
                val errorMessage = when (val exception = it) {
                    is FirebaseAuthInvalidCredentialsException -> "Credenciales inválidas"
                    is FirebaseAuthUserCollisionException -> "El usuario ya está registrado"
                    is FirebaseException -> "Error de Firebase: ${exception.message}"
                    else -> "Error desconocido: ${exception?.message}"
                }
                onError(errorMessage)
            }.collect()
        }
    }




}
