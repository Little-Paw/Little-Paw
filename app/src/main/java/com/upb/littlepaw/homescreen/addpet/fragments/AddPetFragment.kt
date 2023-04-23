package com.upb.littlepaw.homescreen.addpet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.databinding.FragmentAddPetBinding
import com.upb.littlepaw.homescreen.HomeActivity
import com.upb.littlepaw.homescreen.addpet.fragments.viewmodels.AddPetViewModel

class AddPetFragment: Fragment() {
    private lateinit var binding: FragmentAddPetBinding
    private lateinit var homeBinding: ActivityHomeBinding
    private val addPetViewModel: AddPetViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPetBinding.inflate(inflater, container, false)
        homeBinding = ActivityHomeBinding.bind((requireActivity() as HomeActivity).binding.root)
        binding.viewModel = addPetViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener{
            val action = AddPetFragmentDirections.actionAddPetFragmentToAdoptionFragment()
            findNavController().navigate(action)
        }
        binding.menuButton.setOnClickListener{
            homeBinding.drawerLayout.open()
        }
    }
}