package com.upb.littlepaw.homescreen

import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.R

class HomeViewModel: ViewModel() {
    lateinit var onClickMenuButton: () -> Unit
    val sideBarOpen = MutableLiveData<Boolean>()
    val statusBarColor = MutableLiveData<Int>()

    init {
        setSideBarOpen(false)
        setStatusBarColor(R.color.white)
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