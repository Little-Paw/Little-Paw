package com.upb.littlepaw.homescreen

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.upb.littlepaw.*
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragment

class HomeActivity : AppCompatActivity() { //if we do this, we don't need to set the content view in the onCreate method
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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)


        val actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle.syncState()

    }


}