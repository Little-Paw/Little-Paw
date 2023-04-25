package com.upb.littlepaw.homescreen.addpet.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.google.android.material.textview.MaterialTextView
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.databinding.FragmentAddPetBinding
import com.upb.littlepaw.homescreen.HomeActivity
import com.upb.littlepaw.homescreen.HomeViewModel
import com.upb.littlepaw.homescreen.addpet.fragments.viewmodels.AddPetViewModel


class AddPetFragment: Fragment() {
    private lateinit var binding: FragmentAddPetBinding
    private val addPetViewModel: AddPetViewModel by viewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    val fileChooserContract = registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        if (imageUri != null) {
            val addPhotoButton = binding.addPhotoButton

            Glide.with(this)
                .load(imageUri).circleCrop()
                .into(object : CustomTarget<Drawable>() {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: com.bumptech.glide.request.transition.Transition<in Drawable>?
                    ) {
                        addPhotoButton.background = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })

        }
    }

    fun openFileChooser() {
        fileChooserContract.launch("image/*")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPetBinding.inflate(inflater, container, false)
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
            homeViewModel.onClickMenuButton()
        }
        val addPhotoButton = binding.addPhotoButton
        addPhotoButton.setOnClickListener {
            openFileChooser()
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.setStatusBarColor(R.color.primary)
    }

}
