package com.upb.littlepaw.homescreen.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hbb20.countrypicker.config.CPViewConfig
import com.hbb20.countrypicker.models.CPCountry
import com.upb.littlepaw.databinding.FragmentProfileBinding
import com.upb.littlepaw.homescreen.profile.fragments.viewmodels.ProfileViewModel
import com.upb.littlepaw.homescreen.profile.models.User

class ProfileFragment: Fragment() {
    lateinit var binding: FragmentProfileBinding
    val profileViewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentProfileBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = profileViewModel
        binding.saveButtonProfile.setOnClickListener {
            println(profileViewModel.user.value?.name.toString())
            println(profileViewModel.user.value?.email.toString())
            println(profileViewModel.user.value?.country.toString())
        }


    }
}