package com.upb.littlepaw.utils

import androidx.databinding.BindingAdapter
import com.hbb20.CountryPickerView
import com.hbb20.countrypicker.config.CPViewConfig

class BindingAdapters {
    companion object {
        @JvmStatic @BindingAdapter("initialCountry")
        fun setInitialCountry(view: CountryPickerView, country: String) {
            view.cpViewHelper.setInitialCountry(CPViewConfig.InitialSelection.SpecificCountry(country))
        }
    }
}