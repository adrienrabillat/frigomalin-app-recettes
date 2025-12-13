package com.example.frigomalin.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.frigomalin.data.MealDetail
import com.example.frigomalin.data.MealRepository
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    
    private val repository = MealRepository.getInstance()
    
    private val _mealDetail = MutableLiveData<MealDetail?>()
    val mealDetail: LiveData<MealDetail?> = _mealDetail
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    fun loadMealDetails(mealId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
                val detail = repository.getMealDetails(mealId)
                _mealDetail.value = detail
                
                if (detail == null) {
                    _error.value = "Meal not found"
                }
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
                _mealDetail.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }
}
