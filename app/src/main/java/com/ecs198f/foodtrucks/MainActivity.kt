package com.ecs198f.foodtrucks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.ActivityMainBinding
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    val gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, object: JsonDeserializer<LocalDateTime> {
            override fun deserialize(
                json: JsonElement?,
                typeOfT: Type?,
                context: JsonDeserializationContext?
            ): LocalDateTime {
                return LocalDateTime.parse(json!!.asString)
            }
        })
        .registerTypeAdapter(LocalDateTime::class.java, object: JsonSerializer<LocalDateTime> {
            override fun serialize(
                src: LocalDateTime?,
                typeOfSrc: Type?,
                context: JsonSerializationContext?
            ): JsonElement {
                return JsonPrimitive(src!!.toString())
            }
        })
        .create()

    val foodService = Retrofit.Builder()
        .baseUrl("https://api.foodtruck.schedgo.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(FoodService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Food Trucks"
    }
}