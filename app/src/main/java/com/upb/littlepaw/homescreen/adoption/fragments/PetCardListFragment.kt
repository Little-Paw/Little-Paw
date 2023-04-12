package com.upb.littlepaw.homescreen.adoption.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentPetCardListBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragment
import com.upb.littlepaw.homescreen.adoption.adapters.PetCardListRecyclerViewAdapter
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender
import com.upb.littlepaw.homescreen.adoption.models.PetType

/**
 * A fragment representing a list of Items.
 */
class PetCardListFragment : Fragment() {
    lateinit var binding: FragmentPetCardListBinding

    companion object {
        fun newInstance() = PetCardListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_card_list, container, false)
        binding = FragmentPetCardListBinding.bind(view)

        val petList = listOf(
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.FEMALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
            PetCard("Doki", 15, "Husky", PetType.DOG, PetGender.MALE, R.drawable.dog_placeholder, 45),
        )

        with(binding.list) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PetCardListRecyclerViewAdapter(petList)
        }
        return view
    }
}