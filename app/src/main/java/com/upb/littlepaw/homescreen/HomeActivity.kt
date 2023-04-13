package com.upb.littlepaw.homescreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import com.upb.littlepaw.*
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragment

class HomeActivity : AppCompatActivity() { //if we do this, we don't need to set the content view in the onCreate method
    private lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding
    lateinit var drawerLayout:DrawerLayout

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
        setContentView(R.layout.activity_home)
        /*

        window.statusBarColor = getColor(R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            binding.homeActivityLayout.setOnApplyWindowInsetsListener { view, windowInsets ->
                view.updatePadding(top = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars()).top)
                windowInsets
            }
        }

         */
        //drawerLayout = binding.drawerLayout

        //val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        //val navController = navHostFragment.navController
        //binding.navView.setupWithNavController(navController)

        //navController.navigate(R.id.adoptionFragment)
        //supportFragmentManager.replaceFragment(binding.fragmentHomeContainer.id, adoptionFragment)
        /*
        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle.syncState()

         */


        /*

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

         */
    }
    /*
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

     */

}