package com.upb.littlepaw.homescreen.profile.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.hbb20.countrypicker.config.CPViewConfig
import com.hbb20.countrypicker.models.CPCountry
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentProfileBinding
import com.upb.littlepaw.homescreen.HomeViewModel
import com.upb.littlepaw.homescreen.profile.fragments.viewmodels.ProfileViewModel
import com.upb.littlepaw.homescreen.profile.models.User
import com.upb.littlepaw.homescreen.profile.models.UserEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment: Fragment() {
    lateinit var binding: FragmentProfileBinding
    val profileViewModel: ProfileViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    val fileChooseContract = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        Glide.with(this).load(uri).circleCrop().into(object: CustomTarget<Drawable>() {
            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                binding.uploadImageProfile.background = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {
            }
        })
        }

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
        CoroutineScope(Dispatchers.Main).launch {
            profileViewModel.initializeUser(requireContext())
        }
        binding.saveButtonProfile.setOnClickListener {
            println(profileViewModel.user.value?.name?.value?.toString())
            println(profileViewModel.user.value?.email?.value?.toString())
            println(profileViewModel.user.value?.country?.value?.toString())
            val user = UserEntity(profileViewModel.user.value!!)
            profileViewModel.updateUser(requireContext(), user, {view.findNavController().navigate(R.id.adoptionFragment)}, {error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show() })

        }
        binding.changePasswordButtonProfile.setOnClickListener {
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment(profileViewModel.user.value!!))
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

        binding.uploadImageProfile.setOnClickListener{
            fileChooseContract.launch("image/*")
        }


    }



    override fun onResume() {
        super.onResume()
        homeViewModel.setStatusBarColor(R.color.primary)
    }
}