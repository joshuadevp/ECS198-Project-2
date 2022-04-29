package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecs198f.foodtrucks.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime


class FoodTruckListFragment : Fragment() {

    val foodService = Retrofit.Builder()
        .baseUrl("https://api.foodtruck.schedgo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FoodService::class.java)

    private val foodTrucks = listOf(
        FoodTruck(
            "1",
            "Shah's Halal",
            "https://www.palisadescenter.com/wp-content/uploads/sites/17/2018/11/Shahs-Logo-with-Tagline.png",
            3,
            "Silo Patio",
            LocalDateTime.of(2021, 10, 4, 11, 0, 0, 0),
            LocalDateTime.of(2021, 10, 4, 16, 0, 0, 0),
        ),
        FoodTruck(
            "2",
            "Hefty Gyros",
            "https://heartstoppershaunt.com/wp-content/uploads/2020/10/heftygyros-logo-1024x435.png",
            2,
            "West Quad",
            LocalDateTime.of(2021, 10, 4, 11, 0, 0, 0),
            LocalDateTime.of(2021, 10, 4, 15, 0, 0, 0),
        )
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //super.onCreateView(savedInstanceState)

        return inflater.inflate(R.layout.fragment_food_truck_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.truckListView).apply{
            layoutManager = LinearLayoutManager(context)
            adapter = FoodTruckListRecyclerViewAdapter(foodTrucks)
        }


    }

}