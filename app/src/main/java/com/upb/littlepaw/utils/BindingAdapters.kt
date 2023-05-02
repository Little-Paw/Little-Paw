package com.upb.littlepaw.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.hbb20.CountryPickerView
import com.hbb20.countrypicker.config.CPViewConfig

class BindingAdapters {
    companion object {
        @JvmStatic @BindingAdapter("initialCountry")
        fun setInitialCountry(view: CountryPickerView, country: String?) {
            if (country != null) {
                view.cpViewHelper.setInitialCountry(
                    CPViewConfig.InitialSelection.SpecificCountry(
                        country
                    )
                )
            }
        }
        @JvmStatic @InverseBindingAdapter(attribute = "initialCountry")
        fun getInitialCountry(view: CountryPickerView): String? {
            return view.cpViewHelper.selectedCountry.value?.alpha2
        }

        @JvmStatic @BindingAdapter("initialCountryAttrChanged")
        fun setListener(view: CountryPickerView, listener: InverseBindingListener) {
            view.cpViewHelper.selectedCountry.observeForever {
                listener.onChange()
            }
        }
        @JvmStatic @BindingAdapter("srcUrl")
        fun loadUrlImageView(imageView: ImageView, url: String) {
            Glide.with(imageView).load(url).into(imageView)
        }
    }
}