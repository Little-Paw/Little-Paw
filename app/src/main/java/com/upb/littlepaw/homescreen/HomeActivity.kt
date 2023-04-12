package com.upb.littlepaw.homescreen

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.upb.littlepaw.*
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragment
import com.upb.littlepaw.utils.replaceFragment

class HomeActivity : AppCompatActivity(R.layout.activity_home) { //if we do this, we don't need to set the content view in the onCreate method
    private lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    private val adoptionFragment = AdoptionFragment()
    private val addPetFragment = AddPetFragment()
    private val profileFragment = ProfileFragment()
    private val chatbotFragment = ChatbotFragment()

    companion object {
        fun newInstance() = HomeActivity()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            binding.homeActivityLayout.setOnApplyWindowInsetsListener { view, windowInsets ->
                view.updatePadding(top = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top)
                windowInsets
            }
        }

        supportFragmentManager.replaceFragment(binding.petCardListFragment.id, adoptionFragment)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_adoption -> supportFragmentManager.replaceFragment(binding.petCardListFragment.id, adoptionFragment)
                R.id.nav_add_pet -> supportFragmentManager.replaceFragment(binding.petCardListFragment.id, addPetFragment)
                R.id.nav_profile -> supportFragmentManager.replaceFragment(binding.petCardListFragment.id, profileFragment)
                R.id.nav_chatbot -> supportFragmentManager.replaceFragment(binding.petCardListFragment.id, chatbotFragment)
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