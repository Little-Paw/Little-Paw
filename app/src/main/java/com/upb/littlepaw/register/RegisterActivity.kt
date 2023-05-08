package com.upb.littlepaw.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.upb.littlepaw.databinding.ActivityRegisterBinding
import com.upb.littlepaw.homescreen.profile.models.User
import com.upb.littlepaw.homescreen.profile.models.UserEntity
import com.upb.littlepaw.register.viewmodels.RegisterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    val registerViewModel: RegisterViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = registerViewModel
        binding.lifecycleOwner = this
        binding.registerButtonRegisterScreen.setOnClickListener {
            println(registerViewModel.user.value?.name?.value?.toString())
            println(registerViewModel.user.value?.email?.value?.toString())
            println(registerViewModel.user.value?.country?.value?.toString())
            println(registerViewModel.user.value?.password?.value?.toString())
            println(registerViewModel.repeatPassword.value?.toString())
            val user = UserEntity(registerViewModel.user.value!!)
            registerViewModel.createUser(user, { },
                {Toast.makeText(this, "Error creando usuario", Toast.LENGTH_SHORT).show()})
        }

        binding.editTextFullNameRegister.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) {
                registerViewModel.setTouchedFullName(true)
            }
        }

        binding.editTextEmailAddressRegister.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) {
                registerViewModel.setTouchedEmail(true)
            }
        }

        binding.editTextPasswordRegister.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) {
                registerViewModel.setTouchedPassword(true)
            }
        }

        binding.editTextRepeatPasswordRegister.setOnFocusChangeListener{ _, hasFocus ->
            if(hasFocus) {
                registerViewModel.setTouchedRepeatPassword(true)
            }
        }

        registerViewModel.user.value?.name?.observe(this) {
            registerViewModel.validateFullName()
            registerViewModel.validateAll()
        }

        registerViewModel.user.value?.email?.observe(this) {
            registerViewModel.validateEmail()
            registerViewModel.validateAll()
        }

        registerViewModel.user.value?.country?.observe(this) {
            registerViewModel.validateAll()
        }

        registerViewModel.user.value?.password?.observe(this) {
            registerViewModel.validateNewPassword()
            registerViewModel.validateAll()
        }

        registerViewModel.repeatPassword.observe(this) {
            registerViewModel.validateRepeatNewPassword()
            registerViewModel.validateAll()
        }


    }

}