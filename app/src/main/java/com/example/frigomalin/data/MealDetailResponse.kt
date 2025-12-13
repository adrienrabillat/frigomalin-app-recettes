package com.example.frigomalin.data

import com.google.gson.annotations.SerializedName

/**
 * API response wrapper for meal details
 */
data class MealDetailResponse(
    @SerializedName("meals")
    val meals: List<MealDetail>?
)
