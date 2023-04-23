package com.upb.littlepaw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.upb.littlepaw.homescreen.addpet.fragments.AddPetFragment

class MainActivity : AppCompatActivity() {

    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, profileFragment)
        ft.commitAllowingStateLoss()



        val addPetFragment = AddPetFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, addPetFragment).commit()



    }
}