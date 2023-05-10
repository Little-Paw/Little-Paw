package com.upb.littlepaw.homescreen.addpet.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import java.io.ByteArrayOutputStream


class AddPetFragment: Fragment() {
    private lateinit var binding: FragmentAddPetBinding
    private val addPetViewModel: AddPetViewModel by activityViewModel()
    private val homeViewModel: HomeViewModel by activityViewModel()

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

            val inputStream = requireActivity().contentResolver.openInputStream(imageUri)
            val bitmap = android.graphics.BitmapFactory.decodeStream(inputStream)

            val outputStream = ByteArrayOutputStream()
            bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, outputStream)

            val byteArray = outputStream.toByteArray()

            addPetViewModel.pet.value?.image = byteArray

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
            val addPetDialog = SavePetDialog()
            addPetDialog.show(parentFragmentManager, "SavePetDialog")
            addPetViewModel.uploadPet(addPetViewModel.pet.value!!, {
                val action = AddPetFragmentDirections.actionAddPetFragmentToAdoptionFragment()
                findNavController().navigate(action)
                Toast.makeText(context, "Pet posted successfully", Toast.LENGTH_LONG).show()
                addPetDialog.dismiss()
            }, {
                Toast.makeText(context, "Error posting pet", Toast.LENGTH_LONG).show()
                addPetDialog.dismiss()
            })
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
