package com.upb.littlepaw.homescreen

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.contains
import androidx.core.view.forEach
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragment
import com.upb.littlepaw.homescreen.fragments.SideBarFragment
import com.upb.littlepaw.utils.replaceFragment
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    val viewModel: HomeViewModel by viewModel()

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




        val mainFragmentContainer: MaterialCardView = binding.mainFragmentContainer
        val sideBarFragmentContainer: ViewGroup = binding.sidebarFragment

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

        binding.blockerView.setOnClickListener {
            if (viewModel.getSideBarOpen()) {
                viewModel.setSideBarOpen(false)
            }
        }

        viewModel.sideBarOpen.observe(this) {
            if (it) {
                binding.blockerView.visibility = View.VISIBLE
                mainFragmentContainer.radius = 100f
                window.statusBarColor = getColor(R.color.secondary)
                AnimatorSet().apply {
                    playTogether(positionAnimationMainFragmentOpen, scaleAnimationOpen, positionAnimationSideBarFragmentOpen)
                    start()
                }
            } else {
                binding.blockerView.visibility = View.GONE
                window.statusBarColor = getColor(viewModel.getStatusBarColor())
                AnimatorSet().apply {
                    playTogether(positionAnimationMainFragmentClose, scaleAnimationClose, positionAnimationSideBarFragmentClose)
                    start()
                    doOnEnd { binding.mainFragmentContainer.radius = 0f }
                }
            }
        }

        viewModel.statusBarColor.observe(this) {
            if (!viewModel.getSideBarOpen()) {
                window.statusBarColor = getColor(it)
            }
        }

//        onBackPressedDispatcher.addCallback(this) {
//            if (!viewModel.getSideBarOpen() && !containsFragment) { //TODO: check if current fragment is not on the drawer menu
//                viewModel.setSideBarOpen(true)
//            } else {
//                finish()
//            }
//        }
    }
}