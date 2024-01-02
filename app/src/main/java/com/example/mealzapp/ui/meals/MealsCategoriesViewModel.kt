package com.example.mealzapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.MealsRepository
import com.example.model.response.MealCategory
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :ViewModel()  {

    val mealsState:MutableState<List<MealCategory>> = mutableStateOf(emptyList<MealCategory>())

    init {
        viewModelScope.launch() {
            val meals=getMeals()
            mealsState.value=meals
        }
    }
    suspend fun getMeals() : List<MealCategory> {
         return repository.getMeals().categories
    }
}