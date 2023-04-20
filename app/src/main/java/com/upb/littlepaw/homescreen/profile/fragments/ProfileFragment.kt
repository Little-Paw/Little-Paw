package com.upb.littlepaw.homescreen.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hbb20.countrypicker.config.CPViewConfig
import com.hbb20.countrypicker.models.CPCountry
import com.upb.littlepaw.databinding.FragmentProfileBinding
import com.upb.littlepaw.homescreen.profile.models.User

class ProfileFragment: Fragment() {
    lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentProfileBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var user = User("Pepe Madero", "pepe@gmail.com", "pepe123", "CL")
        binding.user = user
        binding.locationPickerProfile.cpViewHelper.onCountryChangedListener = { selectedCountry:CPCountry? ->
            println(selectedCountry?.name)
        }
        super.onViewCreated(view, savedInstanceState)

    }
}