package com.upb.littlepaw.homescreen.adoption.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentPetCardListBinding
import com.upb.littlepaw.databinding.FragmentPetTypeListBinding
import com.upb.littlepaw.homescreen.adoption.adapters.PetCardListRecyclerViewAdapter
import com.upb.littlepaw.homescreen.adoption.adapters.PetTypeIconRecyclerViewAdapter
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType
import com.upb.littlepaw.homescreen.adoption.models.PetTypeIcon

class PetTypeListFragment: Fragment() {
    private lateinit var binding: FragmentPetTypeListBinding
    companion object {
        fun newInstance() = PetTypeListFragment()
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
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
        )


        with(binding.list) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = PetTypeIconRecyclerViewAdapter(petTypeList)
        }
        return view
    }
}