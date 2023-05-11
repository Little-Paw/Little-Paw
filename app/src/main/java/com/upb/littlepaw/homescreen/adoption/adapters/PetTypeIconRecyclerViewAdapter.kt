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
import android.content.Context
import android.util.DisplayMetrics

class PetTypeIconRecyclerViewAdapter(private val petTypesList: List<PetTypeIcon>, private val adoptionViewModel: AdoptionViewModel
) : RecyclerView.Adapter<PetTypeIconRecyclerViewAdapter.ViewHolder>() {

    private var selectedItem = petTypesList.indexOfFirst { it.type == adoptionViewModel.getSelectedPetType() }
    private var recyclerView: RecyclerView? = null
    private var itemDecorator: ItemDecorator? = null
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
        itemDecorator = ItemDecorator(recyclerView.context, 30)
        recyclerView.addItemDecoration(itemDecorator!!)
        (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(selectedItem, 0)
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
            holder.button.iconTint = AppCompatResources.getColorStateList(holder.layout.context, R.color.primary)
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
            if (petTypesList[selectedItem].type != adoptionViewModel.getSelectedPetType()) {
                adoptionViewModel.setSelectedPetType(petTypesList[selectedItem].type)
                adoptionViewModel.notifyPetCardListParamsChanged()
                notifyItemChanged(selectedItem)
            }
            (recyclerView?.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(selectedItem, 0)
        }
    }

    inner class ItemDecorator(private val context: Context, private val spaceBetweenItemsDP: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) != state.itemCount - 1) {
                outRect.right = convertDpToPixel(spaceBetweenItemsDP)
            }
        }
        private fun convertDpToPixel(dp: Int): Int {
            return (dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
        }

    }
}