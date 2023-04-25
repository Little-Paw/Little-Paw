package com.upb.littlepaw.homescreen.adoption

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.ActivityHomeBinding
import com.upb.littlepaw.databinding.FragmentAdoptionBinding
import com.upb.littlepaw.homescreen.HomeActivity
import com.upb.littlepaw.homescreen.adoption.fragments.PetCardListFragment
import com.upb.littlepaw.homescreen.adoption.fragments.PetTypeListFragment
import com.upb.littlepaw.utils.replaceFragment

class AdoptionFragment : Fragment(R.layout.fragment_adoption) {

    private lateinit var binding: FragmentAdoptionBinding
    private lateinit var homeBinding: ActivityHomeBinding


    private val adoptionViewModel: AdoptionViewModel by lazy {
        ViewModelProvider(requireActivity())[AdoptionViewModel::class.java]
    }

    private val petCardListFragment = PetCardListFragment()
    private val selectPetTypeFragment = PetTypeListFragment()

    companion object {
        const val TAG = "AdoptionFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_adoption, container, false)
        binding = FragmentAdoptionBinding.bind(view)
        homeBinding = ActivityHomeBinding.bind((requireActivity() as HomeActivity).binding.root)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        childFragmentManager.replaceFragment(binding.selectPetTypeFragment.id, selectPetTypeFragment, false, PetTypeListFragment.TAG)
        childFragmentManager.replaceFragment(binding.petCardListFragment.id, petCardListFragment, false, PetCardListFragment.TAG)

        binding.petSearchEditText.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                adoptionViewModel.setPetSearchQuery(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.petSearchEditText.setOnEditorActionListener { _, _, _ ->
            adoptionViewModel.notifyPetCardListParamsChanged()
            binding.petSearchEditText.clearFocus()
            val inputManager = requireActivity().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
            inputManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
            true
        }

        binding.menuButton.setOnClickListener {
            homeBinding.drawerLayout.open()
        }

    }

}