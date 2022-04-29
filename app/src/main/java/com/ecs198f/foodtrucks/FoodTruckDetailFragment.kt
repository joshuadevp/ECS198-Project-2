package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime


class FoodTruckDetailFragment : Fragment() {
    private val args: FoodTruckDetailFragmentArgs by navArgs()

    val foodService = Retrofit.Builder()
        .baseUrl("https://api.foodtruck.schedgo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodService::class.java)

    private val foodList= listOf(
        listOf(
            FoodItem(
                "1",
                "1",
                "Thai BBQ",
                "Rice bowl combo with salad",
                100.00,
                false
            ),
            FoodItem(
                "1",
                "1",
                "Lemon Tofu",
                "Vegan rice bowl",
                3.14159,
                true
            )
        ),
        listOf(
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
        Glide.with(this).load(foodTruck.imageResId).into(view.findViewById<ImageView>(R.id.imageView))
        view.findViewById<TextView>(R.id.nameView).text = foodTruck.name
        view.findViewById<TextView>(R.id.locationView).text = foodTruck.location
        view.findViewById<TextView>(R.id.timeView).text = foodTruck.formattedTimeInterval
        var foodItemList = foodList.get(foodTruck.id.toInt()-1)
        view.findViewById<RecyclerView>(R.id.itemListView).apply{
            layoutManager = LinearLayoutManager(context)
            adapter = FoodItemListRecyclerViewAdapter(foodItemList)
        }


    }
}