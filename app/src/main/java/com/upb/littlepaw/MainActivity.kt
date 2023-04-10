package com.upb.littlepaw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addPetFragment = AddPetFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, addPetFragment).commit()

    }
}