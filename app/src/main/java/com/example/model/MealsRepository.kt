package com.example.model

import com.example.model.api.MealsAPI
import com.example.model.response.MealCategoriesResponse
import com.example.model.response.MealCategory


class MealsRepository(private val webService:MealsAPI= MealsAPI()) {
    private var cachedMeals = listOf<MealCategory>()
    suspend fun getMeals(): MealCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }

    fun getMeal(id: String) : MealCategory? {
        return cachedMeals.firstOrNull {
            it.id == id
        }
    }
    companion object{
        @Volatile
        private var instance: MealsRepository?= null
        fun getInstance()= instance?: synchronized(this){
            instance?:MealsRepository().also { instance=it }
        }
    }

}