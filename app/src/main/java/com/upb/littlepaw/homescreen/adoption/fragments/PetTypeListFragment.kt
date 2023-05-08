package com.upb.littlepaw.homescreen.adoption.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentPetTypeListBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionViewModel
import com.upb.littlepaw.homescreen.adoption.adapters.PetTypeIconRecyclerViewAdapter
import com.upb.littlepaw.homescreen.adoption.models.PetType
import com.upb.littlepaw.homescreen.adoption.models.PetTypeIcon
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class PetTypeListFragment: Fragment() {
    private lateinit var binding: FragmentPetTypeListBinding
    private lateinit var recyclerViewPetTypeList: RecyclerView

    private val adoptionViewModel:AdoptionViewModel by activityViewModel()

    companion object {
        fun newInstance() = PetTypeListFragment()
        const val TAG = "PetTypeListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_type_list, container, false)
        binding = FragmentPetTypeListBinding.bind(view)

        val petTypeList = listOf(
            PetTypeIcon(R.drawable.ic_dog, PetType.DOG, "Dogs"),
            PetTypeIcon(R.drawable.ic_cat, PetType.CAT, "Cats"),
            PetTypeIcon(R.drawable.ic_bunny, PetType.BUNNY, "Bunnies"),
            PetTypeIcon(R.drawable.ic_bird, PetType.BIRD, "Birds"),
            PetTypeIcon(R.drawable.ic_reptile, PetType.REPTILE, "Reptiles"),
            PetTypeIcon(R.drawable.ic_others, PetType.OTHER, "Other"),
        )


        with(binding.petTypeListRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = PetTypeIconRecyclerViewAdapter(petTypeList, adoptionViewModel)
            recyclerViewPetTypeList = this
        }
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        recyclerViewPetTypeList.scheduleLayoutAnimation()
    }
}