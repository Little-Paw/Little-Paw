package com.upb.littlepaw.homescreen

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.R
import com.upb.littlepaw.data.repositories.UsersRepository
import com.upb.littlepaw.homescreen.profile.models.User
import com.upb.littlepaw.homescreen.profile.models.UserEntity
import org.apache.commons.lang3.mutable.Mutable

class HomeViewModel: ViewModel() {
    lateinit var onClickMenuButton: () -> Unit
    var user: MutableLiveData<UserEntity> = MutableLiveData<UserEntity>()
    val sideBarOpen = MutableLiveData<Boolean>()
    val statusBarColor = MutableLiveData<Int>()
    val usersRepository = UsersRepository()

    init {
        setSideBarOpen(false)
        setStatusBarColor(R.color.white)
    }



    suspend fun getUser(context: Context) {
        val user = usersRepository.getLoggedUser(context)
        this.user.value = user
    }

    fun setSideBarOpen(state: Boolean) {
        if (sideBarOpen.value != null && sideBarOpen.value == state) return
        sideBarOpen.value = state
    }
    fun getSideBarOpen(): Boolean {
        return sideBarOpen.value!!
    }

    fun setStatusBarColor(color: Int) {
        if (statusBarColor.value != null && statusBarColor.value == color) return
        statusBarColor.value = color
    }

    fun getStatusBarColor(): Int {
        return statusBarColor.value!!
    }
}