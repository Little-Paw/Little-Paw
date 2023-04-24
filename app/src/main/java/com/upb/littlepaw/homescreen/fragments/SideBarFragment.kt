package com.upb.littlepaw.homescreen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.databinding.FragmentSideBarBinding
import com.upb.littlepaw.homescreen.HomeActivity
import com.upb.littlepaw.homescreen.HomeViewModel
import com.upb.littlepaw.homescreen.models.FragmentSelector


class SideBarFragment : Fragment(R.layout.fragment_side_bar) {
    lateinit var binding: FragmentSideBarBinding
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    companion object {
        const val TAG = "SideBarFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSideBarBinding.inflate(inflater, container, false)
        homeBinding = ActivityHomeBinding.bind((requireActivity() as HomeActivity).binding.root)
        homeViewModel = (requireActivity() as HomeActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = parentFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.sideBarButtons.setupWithNavController(navController)

    }
}