package com.example.frigomalin.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealRepository {
    
    private val api: MealApi
    
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        
        api = retrofit.create(MealApi::class.java)
    }
    
    suspend fun searchMealsByIngredient(ingredient: String): List<Meal> {
        return try {
            val response = api.searchMealsByIngredient(ingredient)
            response.meals ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
    
    suspend fun getMealDetails(id: String): MealDetail? {
        return try {
            val response = api.getMealDetails(id)
            response.meals?.firstOrNull()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    
    companion object {
        @Volatile
        private var instance: MealRepository? = null
        
        fun getInstance(): MealRepository {
            return instance ?: synchronized(this) {
                instance ?: MealRepository().also { instance = it }
            }
        }
    }
}
