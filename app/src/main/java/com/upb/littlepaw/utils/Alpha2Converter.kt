package com.upb.littlepaw.utils

import java.util.Locale

object Alpha2Converter {

    fun alpha2ToFullName(alpha2:String):String {
        return Locale("", alpha2).getDisplayCountry(Locale.getDefault())
    }

}