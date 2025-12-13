package com.example.frigomalin.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    
    @GET("filter.php")
    suspend fun searchMealsByIngredient(
        @Query("i") ingredient: String
    ): MealResponse
    
    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") id: String
    ): MealDetailResponse
}
