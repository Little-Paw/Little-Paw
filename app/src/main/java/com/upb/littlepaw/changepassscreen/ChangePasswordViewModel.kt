package com.upb.littlepaw.changepassscreen

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

class ChangePasswordViewModel(val usersRepository: UsersRepository): ViewModel() {
    val user = MutableLiveData<User>(User(MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String>(), MutableLiveData<String?>()))

    val oldPassword = MutableLiveData<String>()
    val showOldPassword = MutableLiveData<Boolean>()
    val errorOldPassword = MutableLiveData<String>()
    val touchedOldPassword = MutableLiveData<Boolean>()

    val newPassword = MutableLiveData<String>()
    val showNewPassword = MutableLiveData<Boolean>()
    val errorNewPassword = MutableLiveData<String>()
    val touchedNewPassword = MutableLiveData<Boolean>()

    val repeatNewPassword = MutableLiveData<String>()
    val showRepeatNewPassword = MutableLiveData<Boolean>()
    val errorRepeatNewPassword = MutableLiveData<String>()
    val touchedRepeatNewPassword = MutableLiveData<Boolean>()

    val buttonEnabled = MutableLiveData<Boolean>()

    init {
        setOldPassword("")
        setShowOldPassword(false)
        setErrorNewPassword("")
        setTouchedOldPassword(false)

        setNewPassword("")
        setShowNewPassword(false)
        setErrorNewPassword("")
        setTouchedNewPassword(false)

        setRepeatNewPassword("")
        setShowRepeatNewPassword(false)
        setErrorRepeatNewPassword("")
        setTouchedRepeatNewPassword(false)

        setButtonEnabled(false)
    }

    fun setOldPassword(oldPassword: String) {
        this.oldPassword.value = oldPassword
    }

    fun setErrorOldPassword(errorOldPassword: String) {
        this.errorOldPassword.value = errorOldPassword
    }

    fun setShowOldPassword(showOldPassword: Boolean) {
        this.showOldPassword.value = showOldPassword
    }

    fun setTouchedOldPassword(touchedOldPassword: Boolean) {
        this.touchedOldPassword.value = touchedOldPassword
    }

    fun setNewPassword(newPassword: String) {
        this.newPassword.value = newPassword
    }

    fun setShowNewPassword(showNewPassword: Boolean) {
        this.showNewPassword.value = showNewPassword
    }

    fun setRepeatNewPassword(repeatNewPassword: String) {
        this.repeatNewPassword.value = repeatNewPassword
    }

    fun setShowRepeatNewPassword(showRepeatNewPassword: Boolean) {
        this.showRepeatNewPassword.value = showRepeatNewPassword
    }

    fun setErrorNewPassword(errorNewPassword: String) {
        this.errorNewPassword.value = errorNewPassword
    }

    fun setErrorRepeatNewPassword(errorRepeatNewPassword: String) {
        this.errorRepeatNewPassword.value = errorRepeatNewPassword
    }

    fun setTouchedNewPassword(touchedNewPassword: Boolean) {
        this.touchedNewPassword.value = touchedNewPassword
    }

    fun setTouchedRepeatNewPassword(touchedRepeatNewPassword: Boolean) {
        this.touchedRepeatNewPassword.value = touchedRepeatNewPassword
    }

    fun setButtonEnabled(buttonEnabled: Boolean) {
        this.buttonEnabled.value = buttonEnabled
    }

    fun validatePasswords(): Boolean {
        val validateNewPassword = validateNewPassword()
        val validateRepeatNewPassword = validateRepeatNewPassword()
        val validateOldPassword = validateOldPassword()
        setButtonEnabled(validateNewPassword && validateRepeatNewPassword && validateOldPassword)
        return validateNewPassword && validateRepeatNewPassword && validateOldPassword
    }

    fun validateOldPassword():Boolean {
        val oldPassword = oldPassword.value.toString()
        val compareOldPassword = user.value?.password?.value.toString()
        return if (oldPassword == compareOldPassword) {
            setErrorOldPassword("")
            true
        } else {
            if(touchedOldPassword.value!!) {
                setErrorOldPassword("The password is incorrect")
            } else {
                setErrorOldPassword("")
            }
            false
        }
    }

    fun validateNewPassword(): Boolean {
        val password = newPassword.value.toString()
        val has8Characters = password.length >= 8
        val has1UpperCase = password.any { it.isUpperCase() }
        val has1LowerCase = password.any { it.isLowerCase() }
        val has1Number = password.any { it.isDigit() }
        val has1SpecialCharacter = password.any { it.isLetterOrDigit().not() }

        return if (has8Characters && has1UpperCase && has1LowerCase && has1Number && has1SpecialCharacter) {
            setErrorNewPassword("")
            true
        } else {
            if(touchedNewPassword.value!!) {
                setErrorNewPassword("The password must have: \n" +
                        (if (has8Characters) "" else "- 8 or more characters \n") +
                        (if (has1UpperCase) "" else "- 1 uppercase letter \n") +
                        (if (has1LowerCase) "" else "- 1 lowercase letter \n") +
                        (if (has1Number) "" else "- 1 number \n") +
                        (if (has1SpecialCharacter) "" else "- 1 special character"))
            } else {
                setErrorNewPassword("")
            }
            false
        }
    }

    fun validateRepeatNewPassword(): Boolean {
        val password = newPassword.value.toString()
        val repeatPassword = repeatNewPassword.value.toString()
        return if (password == repeatPassword) {
            setErrorRepeatNewPassword("")
            true
        } else {
            if(touchedRepeatNewPassword.value!!) {
                setErrorRepeatNewPassword("The passwords do not match")
            } else {
                setErrorRepeatNewPassword("")
            }
            false
        }
    }

    fun updatePassword(user: UserEntity, onSuccess: () -> Unit, onError:(error:String) -> Unit) {
        viewModelScope.launch{
            flow{
                usersRepository.updateUser(user)
                emit(user)
            }.flowOn(Dispatchers.IO).onEach { onSuccess() }.catch { onError.invoke("An error has occurred") }.collect()
        }
    }

}

