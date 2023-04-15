package com.upb.littlepaw.homescreen.adoption.adapters

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.upb.littlepaw.R
import com.upb.littlepaw.databinding.PetTypeItemBinding
import com.upb.littlepaw.homescreen.adoption.AdoptionViewModel
import com.upb.littlepaw.homescreen.adoption.models.PetTypeIcon

class PetTypeIconRecyclerViewAdapter(private val petTypesList: List<PetTypeIcon>, private val adoptionViewModel: AdoptionViewModel
) : RecyclerView.Adapter<PetTypeIconRecyclerViewAdapter.ViewHolder>() {

    private var selectedItem = 0
    private var recyclerView: RecyclerView? = null
    private val itemDecorator = ItemDecorator(110)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetTypeIconRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            PetTypeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        recyclerView.addItemDecoration(itemDecorator)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val petType = petTypesList[position]
        holder.button.icon = AppCompatResources.getDrawable(holder.layout.context, petType.icon)
        holder.nameTextView.text = petType.name

        holder.itemView.isSelected = selectedItem == position

        holder.button.setOnClickListener(holder)

        if (selectedItem == position) {
            holder.button.iconTint = AppCompatResources.getColorStateList(holder.layout.context, R.color.white)
            holder.button.backgroundTintList = AppCompatResources.getColorStateList(holder.layout.context, R.color.primary)
        } else {
            holder.button.iconTint = AppCompatResources.getColorStateList(holder.layout.context, R.color.black)
            holder.button.backgroundTintList = AppCompatResources.getColorStateList(holder.layout.context, R.color.white)
        }
    }

    override fun getItemCount(): Int = petTypesList.size

    inner class ViewHolder(binding: PetTypeItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val layout = binding.root
        var button = binding.petTypeButton
        val nameTextView = binding.petTypeText

        override fun onClick(p0: View?) {
            notifyItemChanged(selectedItem)
            selectedItem = bindingAdapterPosition
            adoptionViewModel.setSelectedPetType(petTypesList[selectedItem].type)
            notifyItemChanged(selectedItem)
            (recyclerView?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(selectedItem, 0)
        }
    }

    // add a decorator to the recycler view
    inner class ItemDecorator(private val spaceBetweenItems: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) != state.itemCount - 1) {
                outRect.right = spaceBetweenItems
            }
        }
    }
}