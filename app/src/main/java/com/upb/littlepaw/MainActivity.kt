package com.upb.littlepaw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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


        intent = Intent(this, AnimalScreenActivity::class.java)
        startActivity(intent)
    }
}