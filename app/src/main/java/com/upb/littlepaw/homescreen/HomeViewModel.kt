package com.upb.littlepaw.homescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    lateinit var onClickMenuButton: () -> Unit
    val sideBarOpen = MutableLiveData<Boolean>()

    init {
        setSideBarOpen(false)
    }
    fun setSideBarOpen(state: Boolean) {
        if (sideBarOpen.value != null && sideBarOpen.value == state) return
        sideBarOpen.value = state
    }
    fun getSideBarOpen(): Boolean {
        return sideBarOpen.value!!
    }
}