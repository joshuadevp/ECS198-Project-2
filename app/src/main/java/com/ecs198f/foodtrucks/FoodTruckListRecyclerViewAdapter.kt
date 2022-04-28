package com.ecs198f.foodtrucks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ecs198f.foodtrucks.databinding.FoodTruckListItemBinding

class FoodTruckListRecyclerViewAdapter(private val items: List<FoodTruck>) :
    RecyclerView.Adapter<FoodTruckListRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(val binding: FoodTruckListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodTruckListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.binding.apply {
                foodTruckListItemTitle.text = it.name
                foodTruckListItemPriceLevel.text = "$".repeat(it.priceLevel)
                foodTruckListItemImage.setImageResource(it.imageResId)
                foodTruckListItemLocation.text = it.location
                foodTruckListItemTime.text = it.formattedTimeInterval
            }
        }

        holder.binding.root.setOnClickListener{
            val foodTruck = items[position]
            val action = FoodTruckListFragmentDirections.actionFoodTruckListFragmentToFoodTruckDetailFragment(foodTruck)

            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = items.size
}