package com.ecs198f.foodtrucks

import android.os.Bundle
import android.util.Log
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
    private var foodList= listOf<FoodItem>()
    private var fragmentAdapter = FoodItemListRecyclerViewAdapter(foodList)

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
        Glide.with(this).load(foodTruck.imageUrl).into(view.findViewById<ImageView>(R.id.imageView))
        view.findViewById<TextView>(R.id.nameView).text = foodTruck.name
        view.findViewById<TextView>(R.id.locationView).text = foodTruck.location
        view.findViewById<TextView>(R.id.timeView).text = foodTruck.formattedTimeInterval
        val foodTruckService = (requireActivity() as MainActivity).foodService

        foodTruckService.listFoodItems(foodTruck.id).enqueue(object : Callback<List<FoodItem>> {
            override fun onResponse(
                call: Call<List<FoodItem>>,
                response: Response<List<FoodItem>>
            ) {
                foodList = response.body()!!
                fragmentAdapter.updateItemList(foodList)
            }

            override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                throw t
            }
        })

        view.findViewById<RecyclerView>(R.id.itemListView).apply{
            layoutManager = LinearLayoutManager(context)
            adapter = fragmentAdapter
        }


    }
}