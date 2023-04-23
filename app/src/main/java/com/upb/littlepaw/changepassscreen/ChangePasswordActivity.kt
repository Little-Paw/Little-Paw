package com.upb.littlepaw.changepassscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity(R.layout.activity_change_password) {
    private lateinit var binding: ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }
}