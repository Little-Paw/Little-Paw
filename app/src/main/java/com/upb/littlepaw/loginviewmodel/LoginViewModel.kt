package com.upb.littlepaw.loginviewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.upb.littlepaw.data.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.regex.Pattern

class LoginViewModel(val usersRepository: UsersRepository) : ViewModel() {
    var email : MutableLiveData<String> = MutableLiveData("")
    var password: MutableLiveData<String> = MutableLiveData("")
    private val auth = Firebase.auth

    fun validate(): Boolean {
        return !email.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
    }
//    fun loginUser(context: Context,email:String, password:String, onSuccess: () -> Unit, onError: (error: String) -> Unit) {
//        viewModelScope.launch {
//            flow{
//              val user = usersRepository.login(context,email,password).first()
//              emit(user)
//            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch { onError.invoke("Email or password incorrect.") }.collect()
//        }
//    }

//    fun loginUser(context: Context, email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Autenticación exitosa
//                    onSuccess()
//                } else {
//                    val errorMessage = when (val exception = task.exception) {
//                        is FirebaseAuthInvalidUserException -> "El usuario no existe"
//                        is FirebaseAuthInvalidCredentialsException -> "Credenciales de inicio de sesión inválidas"
//                        is FirebaseAuthException -> "Error de Firebase: ${exception.message}"
//                        else -> "Error desconocido: ${exception?.message}"
//                    }
//                    onError(errorMessage)
//                }
//            }
//    }

//    fun loginUser(
//        context: Context,
//        email: String,
//        password: String,
//        onSuccess: () -> Unit,
//        onError: (error: String) -> Unit
//    ) {
//        viewModelScope.launch {
//            flow {
//                auth.signInWithEmailAndPassword(email, password).await()
//                val user = usersRepository.login(context, email, password).first()
//                emit(user)
//            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch { onError.invoke("Email or password incorrect.") }.collect()
//        }
//    }

    fun loginUser(email: String, password: String, onSuccess: () -> Unit, onError: (error: String) -> Unit) {
        viewModelScope.launch {
            flow {
                val user = usersRepository.login(email, password).first()
                emit(user)
            }.flowOn(Dispatchers.IO).catch { error ->
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onSuccess()
                    } else {
                        val errorMessage = when (val exception = task.exception) {
                            is FirebaseAuthInvalidUserException -> "El usuario no existe"
                            is FirebaseAuthInvalidCredentialsException -> "Correo o contraseña incorrectos"
                            is FirebaseAuthException -> "Error de Firebase: ${exception.message}"
                            else -> "Error desconocido: ${exception?.message}"
                        }
                        onError.invoke(errorMessage)
                    }
                }
            }.collect { user ->
                onSuccess()
            }
        }
    }
}