package com.example.frigomalin.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frigomalin.data.Meal
import com.example.frigomalin.data.MealRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    
    private val repository = MealRepository.getInstance()
    
    private val _meals = MutableLiveData<List<Meal>>()
    val meals: LiveData<List<Meal>> = _meals
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    fun searchMeals(ingredient: String) {
        if (ingredient.isBlank()) {
            _meals.value = emptyList()
            _error.value = null
            return
        }
        
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                val results = repository.searchMealsByIngredient(ingredient)
                _meals.value = results
                
                if (results.isEmpty()) {
                    _error.value = "No results found"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
                _meals.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
