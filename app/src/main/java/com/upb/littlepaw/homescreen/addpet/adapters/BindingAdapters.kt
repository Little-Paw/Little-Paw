package com.upb.littlepaw.homescreen.addpet.adapters

import android.widget.CheckBox
import androidx.databinding.BindingAdapter

@BindingAdapter("checkedEnum")
fun setCheckedEnum(checkBox: CheckBox, enumValue: Enum<*>) {
    checkBox.isChecked = (checkBox.tag as? Enum<*>) == enumValue
    checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
        if (isChecked) {
            checkBox.tag = enumValue
        }
    }
}