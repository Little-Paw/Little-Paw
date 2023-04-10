package com.upb.littlepaw
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

internal class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(

            {
                val intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()


            }, 2000


        )



    }
}