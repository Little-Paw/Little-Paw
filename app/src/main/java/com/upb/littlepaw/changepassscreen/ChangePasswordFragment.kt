package com.upb.littlepaw.changepassscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentChangePasswordBinding
import com.upb.littlepaw.homescreen.profile.models.UserEntity

class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {
    private lateinit var binding: FragmentChangePasswordBinding
    private val viewModel: ChangePasswordViewModel by viewModels()
    val args: ChangePasswordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.primary)
        viewModel.user.value = args.user
        binding.editTextOldPassword.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            if (binding.editTextOldPassword.text != null && binding.editTextOldPassword.text.toString() != "") {
                binding.editTextOldPassword.requestFocus()
                binding.editTextOldPassword.setSelection(binding.editTextOldPassword.text.toString().length)
            }
        }

        binding.editTextNewPassword.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            if (binding.editTextNewPassword.text != null && binding.editTextNewPassword.text.toString() != "") {
                binding.editTextNewPassword.requestFocus()
                binding.editTextNewPassword.setSelection(binding.editTextNewPassword.text.toString().length)
            }
        }

        binding.editTextRepeatNewPassword.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            if (binding.editTextRepeatNewPassword.text != null && binding.editTextRepeatNewPassword.text.toString() != "") {
                binding.editTextRepeatNewPassword.requestFocus()
                binding.editTextRepeatNewPassword.setSelection(binding.editTextRepeatNewPassword.text.toString().length)
            }
        }

        binding.editTextOldPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                viewModel.setTouchedOldPassword(true)
            }
        }

        binding.editTextNewPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                viewModel.setTouchedNewPassword(true)
            }
        }

        binding.editTextRepeatNewPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                viewModel.setTouchedRepeatNewPassword(true)
            }
        }

        binding.buttonSavePassword.setOnClickListener {
            viewModel.user.value?.password = viewModel.newPassword
            val user = UserEntity(viewModel.user.value!!)
            viewModel.updatePassword(requireContext(), user, {findNavController().navigate(R.id.profileFragment)}, {error ->
                Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
            })
        }

        viewModel.oldPassword.observe(viewLifecycleOwner) {
            viewModel.validatePasswords()
        }

        viewModel.newPassword.observe(viewLifecycleOwner) {
            viewModel.validatePasswords()
        }

        viewModel.repeatNewPassword.observe(viewLifecycleOwner) {
            viewModel.validatePasswords()
        }
    }
}