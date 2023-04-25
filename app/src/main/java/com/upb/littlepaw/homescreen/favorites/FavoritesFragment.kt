package com.upb.littlepaw.homescreen.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentFavoritesBinding
import com.upb.littlepaw.homescreen.HomeActivity
import com.upb.littlepaw.homescreen.HomeViewModel
import com.upb.littlepaw.homescreen.favorites.fragments.PetCardFavListFragment
import com.upb.littlepaw.utils.replaceFragment

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private lateinit var binding: FragmentFavoritesBinding
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val viewModel: FavoritesViewModel by viewModels()

    private val petCardFavListFragment = PetCardFavListFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.replaceFragment(binding.petCardListFragment.id, petCardFavListFragment, false, PetCardFavListFragment.TAG)

        binding.menuButton.setOnClickListener {
            homeViewModel.onClickMenuButton()
        }

        binding.profileIconButton.setOnClickListener {
            findNavController().navigate(FavoritesFragmentDirections.actionFavoritesFragmentToProfileFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.setStatusBarColor(R.color.white)
    }

}