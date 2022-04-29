package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime


class FoodTruckDetailFragment : Fragment() {
    private val args: FoodTruckDetailFragmentArgs by navArgs()

    private val foodItems = listOf(
        FoodItem(
            "1",
            "1",
            "Nachos",
            "Cheese and chips",
            100.00,
            false
        ),
        FoodItem(
            "1",
            "1",
            "Hot Dog",
            "Cheese and chips",
            3.14159,
            true
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_truck_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodTruck = args.foodTruck
        //view.findViewById<>()
        view.findViewById<RecyclerView>(R.id.itemListView).apply{
            layoutManager = LinearLayoutManager(context)
            adapter = FoodItemListRecyclerViewAdapter(foodItems)
        }


    }
}