package com.example.frigomalin.data

import com.google.gson.annotations.SerializedName

/**
 * Basic meal model from search results
 */
data class Meal(
    @SerializedName("idMeal")
    val id: String,
    
    @SerializedName("strMeal")
    val name: String,
    
    @SerializedName("strMealThumb")
    val thumbnail: String
)
