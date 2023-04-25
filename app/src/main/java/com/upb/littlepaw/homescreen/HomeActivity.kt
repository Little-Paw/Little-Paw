package com.upb.littlepaw.homescreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.forEach
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.upb.littlepaw.*
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.databinding.FragmentSideBarBinding
import com.upb.littlepaw.homescreen.fragments.SideBarFragment
import com.upb.littlepaw.utils.replaceFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val viewModel: HomeViewModel by viewModels()

    private val sideBarFragment = SideBarFragment()

    @RequiresApi(Build.VERSION_CODES.R)
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

        supportFragmentManager.replaceFragment(binding.sidebarFragment.id, sideBarFragment, false, SideBarFragment.TAG)




        val mainFragmentContainer: ViewGroup = findViewById(R.id.nav_host_fragment)
        val sideBarFragmentContainer: ViewGroup = findViewById(R.id.sidebar_fragment)

        val displayMetrics = resources.displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val scaleSize = 0.7f

        val positionAnimationMainFragmentOpen = ObjectAnimator.ofFloat(mainFragmentContainer, View.TRANSLATION_X, 0f, screenWidth/2f*scaleSize)
            .apply { duration = 500 }
        val positionAnimationSideBarFragmentOpen = ObjectAnimator.ofFloat(sideBarFragmentContainer, View.TRANSLATION_X, -screenWidth.toFloat(), 0f)
            .apply { duration = 500 }
        val scaleAnimationOpen = ObjectAnimator.ofPropertyValuesHolder(
            mainFragmentContainer, PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, scaleSize),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, scaleSize)
        ).apply { duration = 500 }

        val positionAnimationMainFragmentClose = ObjectAnimator.ofFloat(mainFragmentContainer, View.TRANSLATION_X, screenWidth/2f*scaleSize, 0f)
            .apply { duration = 500 }

        val positionAnimationSideBarFragmentClose = ObjectAnimator.ofFloat(sideBarFragmentContainer, View.TRANSLATION_X, 0f, -screenWidth.toFloat())

        val scaleAnimationClose = ObjectAnimator.ofPropertyValuesHolder(
            mainFragmentContainer, PropertyValuesHolder.ofFloat(View.SCALE_X, scaleSize, 1f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, scaleSize, 1f)
        ).apply { duration = 500 }




        viewModel.onClickMenuButton = {
            viewModel.setSideBarOpen(!viewModel.getSideBarOpen())
        }

        viewModel.sideBarOpen.observe(this) {
            println("sideBarOpen: $it")
            if (it) {
                window.statusBarColor = getColor(R.color.secondary)
                AnimatorSet().apply {
                    playTogether(positionAnimationMainFragmentOpen, scaleAnimationOpen, positionAnimationSideBarFragmentOpen)
                    start()
                }
            } else {
                window.statusBarColor = getColor(R.color.white) //TODO: change to current fragment background color
                AnimatorSet().apply {
                    playTogether(positionAnimationMainFragmentClose, scaleAnimationClose, positionAnimationSideBarFragmentClose)
                    start()
                }
            }
        }
    }
}