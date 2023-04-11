package com.upb.littlepaw.homescreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.upb.littlepaw.*
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragment
import com.upb.littlepaw.utils.replaceFragment

class HomeActivity : AppCompatActivity(R.layout.activity_home) { //if we do this, we don't need to set the content view in the onCreate method
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    private val adoptionFragment = AdoptionFragment()
    private val addPetFragment = AddPetFragment()
    private val profileFragment = ProfileFragment()
    private val chatbotFragment = ChatbotFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.replaceFragment(binding.fragmentContainerView.id, adoptionFragment)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_adoption -> supportFragmentManager.replaceFragment(binding.fragmentContainerView.id, adoptionFragment)
                R.id.nav_add_pet -> supportFragmentManager.replaceFragment(binding.fragmentContainerView.id, addPetFragment)
                R.id.nav_profile -> supportFragmentManager.replaceFragment(binding.fragmentContainerView.id, profileFragment)
                R.id.nav_chatbot -> supportFragmentManager.replaceFragment(binding.fragmentContainerView.id, chatbotFragment)
                R.id.nav_animal_screen -> {
                    val intent = Intent(this, AnimalScreenActivity::class.java)
                    startActivity(intent)
                }
            }
            binding.drawerLayout.closeDrawers()
            true
        }
    }

}