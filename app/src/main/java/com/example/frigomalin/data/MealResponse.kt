package com.example.frigomalin.data

import com.google.gson.annotations.SerializedName

/**
 * API response wrapper for meal list
 */
data class MealResponse(
    @SerializedName("meals")
    val meals: List<Meal>?
)
