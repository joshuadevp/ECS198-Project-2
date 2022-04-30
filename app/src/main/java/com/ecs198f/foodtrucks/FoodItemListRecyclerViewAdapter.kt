package com.ecs198f.foodtrucks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ecs198f.foodtrucks.databinding.FoodItemBinding

class FoodItemListRecyclerViewAdapter(private var items: List<FoodItem>) :
    RecyclerView.Adapter<FoodItemListRecyclerViewAdapter.ViewHolder>() {

    fun updateItemList(food: List<FoodItem>) {
        this.items = food
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.binding.apply {
                itemName.text = it.name
                itemPrice.text = '$' + it.price.toString() + " ("+(if (it.taxIncluded) "tax included" else "tax not included") +")"
                itemDesc.text = it.description
            }
        }
    }

    override fun getItemCount() = items.size
}