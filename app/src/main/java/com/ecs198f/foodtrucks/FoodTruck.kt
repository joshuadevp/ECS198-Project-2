package com.ecs198f.foodtrucks

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodTruck(
    val id: String,
    val name: String,
    val imageUrl: String,
    val priceLevel: Int,
    val location: String,
    val openTime: LocalDateTime,
    val closeTime: LocalDateTime
): Parcelable {
    val formattedTimeInterval: String
        get() = "${openTime.format(timeOnlyFormatter)} - ${closeTime.format(dateTimeFormatter)}"

    companion object: Parceler<FoodTruck> {
        private val timeOnlyFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("hh:mm a")

        private val dateTimeFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("hh:mm a, EEE, MMM d")

        override fun create(parcel: Parcel): FoodTruck {
            TODO("Not yet implemented")
        }

        override fun FoodTruck.write(parcel: Parcel, flags: Int) {
            TODO("Not yet implemented")
        }
    }
}
