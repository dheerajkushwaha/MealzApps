package com.example.model.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MealCategoriesResponse(
    @SerializedName("categories")
    val categories: List<MealCategory>
)
@Keep
data class MealCategory(
    @SerializedName("idCategory")
    val id: String?,
    @SerializedName("strCategory")
    val name: String?,
    @SerializedName("strCategoryDescription")
    val description: String?,
    @SerializedName("strCategoryThumb")
    val imageUrl: String?
)