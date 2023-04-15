package com.upb.littlepaw.homescreen.adoption.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentPetCardListBinding
import com.upb.littlepaw.databinding.FragmentPetTypeListBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionViewModel
import com.upb.littlepaw.homescreen.adoption.adapters.PetCardListRecyclerViewAdapter
import com.upb.littlepaw.homescreen.adoption.adapters.PetTypeIconRecyclerViewAdapter
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType
import com.upb.littlepaw.homescreen.adoption.models.PetTypeIcon

class PetTypeListFragment: Fragment() {
    private lateinit var binding: FragmentPetTypeListBinding

    private val adoptionViewModel: AdoptionViewModel by lazy {
        ViewModelProvider(requireActivity())[AdoptionViewModel::class.java]
    }
    companion object {
        fun newInstance() = PetTypeListFragment()
        const val TAG = "PetTypeListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_card_list, container, false)
        binding = FragmentPetTypeListBinding.bind(view)

        val petTypeList = listOf(
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_cat, PetType.CAT, "Cats"),
            PetTypeIcon(R.drawable.ic_bunny, PetType.BUNNY, "Bunnies"),
            PetTypeIcon(R.drawable.ic_bird, PetType.BIRD, "Birds"),
            PetTypeIcon(R.drawable.ic_reptile, PetType.REPTILE, "Reptiles"),
            PetTypeIcon(R.drawable.ic_others, PetType.OTHER, "Other"),
        )


        with(binding.list) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = PetTypeIconRecyclerViewAdapter(petTypeList, adoptionViewModel)
        }
        return view
    }
}