package com.upb.littlepaw.homescreen.adoption.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import androidx.navigation.findNavController
import com.google.android.material.card.MaterialCardView
import com.upb.littlepaw.R

import com.upb.littlepaw.databinding.FragmentPetCardBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionFragmentDirections
import com.upb.littlepaw.homescreen.adoption.models.PetCard
import com.upb.littlepaw.homescreen.adoption.models.PetGender

class PetCardListRecyclerViewAdapter(
    private var petList: List<PetCard>
) : RecyclerView.Adapter<PetCardListRecyclerViewAdapter.ViewHolder>() {

    private var recyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentPetCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pet = petList[position]
        holder.nameTitleTextView.text = pet.name
        holder.breedSubtitleTextView.text = pet.breed
        holder.ageTextView.text = pet.age.toString() + " years old"
        holder.distanceTextView.text = "Distance: " + pet.distanceMeters.toString() + " km"
        holder.genderIconImage.setImageResource(if(pet.gender === PetGender.MALE) R.drawable.ic_male_symbol else R.drawable.ic_female_symbol)
        holder.cardImage.setImageResource(pet.image)

        holder.cardImageContainer.setOnClickListener {
            holder.cardImageContainer.findNavController().navigate(AdoptionFragmentDirections.actionAdoptionFragmentToAnimalScreenFragment(pet))
        }
        holder.cardContainer.setOnClickListener{
            holder.cardImageContainer.findNavController().navigate(AdoptionFragmentDirections.actionAdoptionFragmentToAnimalScreenFragment(pet))
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePetCardList(petList: List<PetCard>){
        this.petList = petList
        notifyDataSetChanged()
        recyclerView?.scheduleLayoutAnimation()
    }

    override fun getItemCount(): Int = petList.size

    inner class ViewHolder(binding: FragmentPetCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.root
        val cardImage: ImageView = binding.petImage
        val nameTitleTextView: TextView = binding.nameTitle
        val breedSubtitleTextView: TextView = binding.breedSubtitle
        val ageTextView: TextView = binding.ageText
        val distanceTextView: TextView = binding.distanceText
        val genderIconImage: ImageView = binding.genderIconImage
        val cardImageContainer: MaterialCardView = binding.cardImageView
        val cardContainer:MaterialCardView = binding.cardView


        override fun toString(): String {
            return super.toString() + " '" + breedSubtitleTextView.text + "'"
        }
    }

}