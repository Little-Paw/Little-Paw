package com.upb.littlepaw.changepassscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentChangePasswordBinding

class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {
    private lateinit var binding: FragmentChangePasswordBinding
    private val viewModel: ChangePasswordViewModel by viewModels()

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

        viewModel.newPassword.observe(viewLifecycleOwner) {
            viewModel.validatePasswords()
        }

        viewModel.repeatNewPassword.observe(viewLifecycleOwner) {
            viewModel.validatePasswords()
        }
    }
}