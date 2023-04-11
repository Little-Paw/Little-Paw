package com.upb.littlepaw.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.upb.littlepaw.R

fun FragmentManager.replaceFragment(@IdRes container: Int, fragment: Fragment) {
    val ft = beginTransaction()
    if (!fragments.contains(fragment)) {
        ft.add(container, fragment)
    }
    fragments.forEach {
        if (it == fragment) ft.show(it) else ft.hide(it)
    }
    ft.commit()
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