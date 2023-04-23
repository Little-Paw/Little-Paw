package com.upb.littlepaw.homescreen.adoption.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.FragmentPetCardListBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragmentDirections
import com.upb.littlepaw.homescreen.adoption.AdoptionViewModel
import com.upb.littlepaw.homescreen.adoption.adapters.PetCardListRecyclerViewAdapter

class PetCardListFragment : Fragment() {
    lateinit var binding: FragmentPetCardListBinding
    lateinit var recyclerViewPetCardList: RecyclerView

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
            adapter = PetCardListRecyclerViewAdapter(adoptionViewModel.getFilteredPetCardsList()) {
                findNavController().navigate(AdoptionFragmentDirections.actionAdoptionFragmentToAnimalScreenFragment(it))
            }
            recyclerViewPetCardList = this
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adoptionViewModel.setOnNotifyPetCarListParamsChanged {
            (binding.petCardListRv.adapter as PetCardListRecyclerViewAdapter).updatePetCardList(adoptionViewModel.getFilteredPetCardsList())
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        recyclerViewPetCardList.scrollToPosition(0)
        recyclerViewPetCardList.scheduleLayoutAnimation()
    }
}