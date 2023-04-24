package com.upb.littlepaw.homescreen.profile.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.hbb20.countrypicker.config.CPViewConfig
import com.hbb20.countrypicker.models.CPCountry
import com.upb.littlepaw.R
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
       binding.lifecycleOwner = this
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = profileViewModel
        binding.saveButtonProfile.setOnClickListener {
            println(profileViewModel.user.value?.name?.value?.toString())
            println(profileViewModel.user.value?.email?.value?.toString())
            println(profileViewModel.user.value?.country?.value?.toString())
            //view.findNavController().navigate(R.id.adoptionFragment)
        }
        binding.editTextFullNameProfile.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) {
                profileViewModel.setTouchedFullName(true)
            }
        }
        binding.editTextEmailAddressProfile.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) {
                profileViewModel.setTouchedEmail(true)
            }
        }
        profileViewModel.user.value?.name?.observe(viewLifecycleOwner) {
            profileViewModel.validateFullName()
            profileViewModel.validateAll()
        }

        profileViewModel.user.value?.email?.observe(viewLifecycleOwner) {
            profileViewModel.validateEmail()
            profileViewModel.validateAll()
        }

        profileViewModel.user.value?.country?.observe(viewLifecycleOwner) {
            profileViewModel.validateAll()
        }


    }
}