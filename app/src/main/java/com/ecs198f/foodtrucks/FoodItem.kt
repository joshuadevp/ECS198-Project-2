package com.ecs198f.foodtrucks

data class FoodItem(
    var id: String,
    var truckId: String,
    var name: String,
    var description: String,
    var price: Double,
    var taxIncluded: Boolean
)
