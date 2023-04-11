package com.upb.littlepaw.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragment
import com.upb.littlepaw.utils.replaceFragment

class HomeActivity : AppCompatActivity(R.layout.activity_home) { //if we do this, we don't need to set the content view in the onCreate method
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    private val adoptionFragment = AdoptionFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.replaceFragment(binding.fragmentContainerView.id, adoptionFragment)
    }
}