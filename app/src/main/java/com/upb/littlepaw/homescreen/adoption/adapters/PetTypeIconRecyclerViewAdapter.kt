package com.upb.littlepaw.homescreen.adoption.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.upb.littlepaw.databinding.PetTypeItemBinding
import com.upb.littlepaw.homescreen.adoption.models.PetTypeIcon

class PetTypeIconRecyclerViewAdapter(private val petTypesList: List<PetTypeIcon>
) : RecyclerView.Adapter<PetTypeIconRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetTypeIconRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            PetTypeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val petType = petTypesList[position]
        holder.button.icon = AppCompatResources.getDrawable(holder.layout.context, petType.icon)
        holder.nameTextView.text = petType.name
    }

    override fun getItemCount(): Int = petTypesList.size

    inner class ViewHolder(binding: PetTypeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.root
        var button = binding.petTypeButton
        val nameTextView = binding.petTypeText

    }
}