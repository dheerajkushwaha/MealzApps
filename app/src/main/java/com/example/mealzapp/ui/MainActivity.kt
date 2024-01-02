package com.example.mealzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mealzapp.ui.detail.MealDetailsScreen
import com.example.mealzapp.ui.detail.MealDetailsViewModel
import com.example.mealzapp.ui.meals.MealsCategoryScreen
import com.example.mealzapp.ui.theme.MealzAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealzAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FoodzApp()
                }
            }
        }
    }
}

@Composable
private fun FoodzApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "destination_meal_list") {
        composable(route = "destination_meal_list") {
            MealsCategoryScreen(){
                navigationMealId->
                navController.navigate("destination_meal_detail/$navigationMealId")
            }
        }
        composable(
            route = "destination_meal_detail/{meal_category_id}",
            arguments = listOf(navArgument("meal_category_id") {
                type = NavType.StringType
            })
        ) {
            val viewModel : MealDetailsViewModel = viewModel()
            MealDetailsScreen(viewModel.mealState.value)
        }
    }

}
