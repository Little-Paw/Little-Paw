package com.upb.littlepaw.homescreen.adoption.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentPetCardListBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionViewModel
import com.upb.littlepaw.homescreen.adoption.adapters.PetCardListRecyclerViewAdapter

class PetCardListFragment : Fragment() {
    lateinit var binding: FragmentPetCardListBinding

    private val adoptionViewModel: AdoptionViewModel by lazy {
        ViewModelProvider(requireActivity())[AdoptionViewModel::class.java]
    }

    companion object {
        const val TAG = "PetCardListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pet_card_list, container, false)
        binding = FragmentPetCardListBinding.bind(view)

        with(binding.petCardListRv) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = PetCardListRecyclerViewAdapter(adoptionViewModel.getFilteredPetCardsList())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adoptionViewModel.setOnNotifyPetCarListParamsChanged {
            (binding.petCardListRv.adapter as PetCardListRecyclerViewAdapter).updatePetCardList(adoptionViewModel.getFilteredPetCardsList())
        }

    }
}