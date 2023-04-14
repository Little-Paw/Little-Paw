package com.upb.littlepaw.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.upb.littlepaw.R

fun FragmentManager.replaceFragment(
    @IdRes containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = false,
    tag: String? = null
) {
    beginTransaction().apply {
        replace(containerViewId, fragment, tag)
        if (addToBackStack) {
            addToBackStack(tag)
        }
        setTransition(FragmentTransaction.TRANSIT_NONE)
        commit()
    }
}

fun FragmentManager.removeLastFragment() {
    if (fragments.size <= 1) {
        return
    }
    val ft = beginTransaction()
    val fragmentToRemove = fragments.last()
    ft.hide(fragmentToRemove)
    ft.remove(fragmentToRemove)
    ft.commit()
}