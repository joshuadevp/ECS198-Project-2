package com.ecs198f.foodtrucks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecs198f.foodtrucks.databinding.ActivityMainBinding
import com.google.gson.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime


class FoodTruckListFragment : Fragment() {
    private var foodTrucks = listOf<FoodTruck>()
    private var fragmentAdapter = FoodTruckListRecyclerViewAdapter(foodTrucks)

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
        val foodTruckService = (requireActivity() as MainActivity).foodService

        foodTruckService.listFoodTrucks().enqueue(object : Callback<List<FoodTruck>> {
            override fun onResponse(
                call: Call<List<FoodTruck>>,
                response: Response<List<FoodTruck>>
            ) {
                foodTrucks = response.body()!!
                fragmentAdapter.updateFoodTrucks(foodTrucks)
                Log.i("ERROR", "size" + foodTrucks.size)
            }

            override fun onFailure(call: Call<List<FoodTruck>>, t: Throwable) {
                throw t
            }
        })

        view.findViewById<RecyclerView>(R.id.truckListView).apply{
            layoutManager = LinearLayoutManager(context)
            val recyclerView = FoodTruckListRecyclerViewAdapter(foodTrucks)
            adapter = fragmentAdapter
        }
    }

}