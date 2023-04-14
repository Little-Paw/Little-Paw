package com.upb.littlepaw.homescreen.adoption

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.databinding.FragmentAdoptionBinding
import com.upb.littlepaw.homescreen.HomeActivity
import com.upb.littlepaw.homescreen.adoption.fragments.PetCardListFragment
import com.upb.littlepaw.homescreen.adoption.fragments.PetTypeListFragment
import com.upb.littlepaw.utils.replaceFragment

class AdoptionFragment : Fragment(R.layout.fragment_adoption) {

    private lateinit var binding: FragmentAdoptionBinding
    private lateinit var viewModel: AdoptionViewModel
    private lateinit var homeBinding: ActivityHomeBinding

    private val petCardListFragment = PetCardListFragment()
    private val selectPetTypeFragment = PetTypeListFragment()

    companion object {
        fun newInstance() = AdoptionFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adoption, container, false)
        binding = FragmentAdoptionBinding.bind(view)
        homeBinding = ActivityHomeBinding.bind((requireActivity() as HomeActivity).binding.root)
        viewModel = ViewModelProvider(this)[AdoptionViewModel::class.java]
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.replaceFragment(binding.petCardListFragment.id, petCardListFragment)
        childFragmentManager.replaceFragment(binding.selectPetTypeFragment.id, selectPetTypeFragment)

        binding.menuButton.setOnClickListener {
            homeBinding.drawerLayout.open()
        }

    }

}