package com.upb.littlepaw.homescreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.upb.littlepaw.homescreen.models.FragmentSelector

class HomeViewModel: ViewModel() {
    lateinit var onClickMenuButton: () -> Unit
    val sideBarOpen = MutableLiveData<Boolean>()
    val fragmentSelector = MutableLiveData<FragmentSelector>()

    init {
        setSideBarOpen(false)
        setFragmentSelector(FragmentSelector.ADOPTION)
    }
    fun setSideBarOpen(state: Boolean) {
        if (sideBarOpen.value != null && sideBarOpen.value == state) return
        sideBarOpen.value = state
    }
    fun getSideBarOpen(): Boolean {
        return sideBarOpen.value!!
    }

    fun setFragmentSelector(fragmentSelector: FragmentSelector) {
        this.fragmentSelector.value = fragmentSelector
    }

    fun getFragmentSelector(): FragmentSelector {
        return fragmentSelector.value!!
    }
}