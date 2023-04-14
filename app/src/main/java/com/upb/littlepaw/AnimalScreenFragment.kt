package com.upb.littlepaw

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.upb.littlepaw.homescreen.adoption.models.PetGender


class AnimalScreenFragment : Fragment() {

    val args:AnimalScreenFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_animal_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.animalNameAnimalScreen).text = args.petCard.name
        view.findViewById<TextView>(R.id.animalBreedAnimalScreen).text = args.petCard.breed
        view.findViewById<TextView>(R.id.animalAgeAnimalScreen).text = "${args.petCard.age} years old"
        view.findViewById<ImageView>(R.id.animalGenderAnimalScreen).setImageResource(
            when(args.petCard.gender) {
                PetGender.MALE -> R.drawable.ic_male_symbol
                PetGender.FEMALE -> R.drawable.ic_female_symbol
            }
        )
        view.findViewById<ImageView>(R.id.animalImageAnimalScreen).setImageResource(args.petCard.image)
    }

}