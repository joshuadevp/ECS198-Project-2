package com.ecs198f.foodtrucks
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodService {
    @GET("/food-trucks")
    fun listFoodTrucks(): Call<List<FoodTruck>>

    @POST("/food-trucks")
    fun createFoodTruck(@Body foodTruck: FoodTruck): Call<Unit>

    @GET("/food-trucks/{id}")
    fun getFoodTruck(@Path("id") id: String) : Call<FoodTruck>

    @GET("/food-trucks/{truckId}/items")
    fun listFoodItems(@Path("truckId") id: String): Call<List<FoodItem>>

    @POST("/food-trucks/{truckId}/items")
    fun createFoodItems(@Path("truckId") id: String, @Body foodItem: FoodItem): Call<Unit>

}