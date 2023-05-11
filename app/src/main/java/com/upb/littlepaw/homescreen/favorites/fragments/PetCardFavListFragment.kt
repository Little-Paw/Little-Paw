package com.upb.littlepaw.homescreen.favorites.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentPetCardListBinding
import com.upb.littlepaw.homescreen.favorites.FavoritesViewModel
import com.upb.littlepaw.homescreen.adoption.adapters.PetCardListRecyclerViewAdapter
import com.upb.littlepaw.homescreen.favorites.FavoritesFragmentDirections

class PetCardFavListFragment : Fragment() {
    lateinit var binding: FragmentPetCardListBinding

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    companion object {
        const val TAG = "PetCardFavListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_card_list, container, false)
        binding = FragmentPetCardListBinding.bind(view)

        with(binding.petCardListRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PetCardListRecyclerViewAdapter(favoritesViewModel.getPetCardFavsList()) {
                findNavController().navigate(FavoritesFragmentDirections.actionFavoritesFragmentToAnimalScreenFragment(it))
            }
        }

        return view
    }
}