package com.example.mealzapp.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.model.MealsRepository
import com.example.model.response.MealCategoriesResponse
import com.example.model.response.MealCategory

class MealDetailsViewModel(private val savedStateHandle:SavedStateHandle,

    ) : ViewModel(){
    val mealState = mutableStateOf<MealCategory?>(null)
    private val repository: MealsRepository = MealsRepository.getInstance()
    init {
        val mealId=savedStateHandle.get<String>("meal_category_id")?:""
        mealState.value =  repository.getMeal(mealId)
    }
}