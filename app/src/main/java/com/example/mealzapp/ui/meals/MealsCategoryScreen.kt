package com.example.mealzapp.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material3.ContentAlpha
import coil.compose.AsyncImage
import com.example.mealzapp.ui.theme.MealzAppTheme
import com.example.model.response.MealCategory

@Composable
fun MealsCategoryScreen(navigationCallBack:(String)->Unit) {
    val viewModel : MealsCategoriesViewModel = viewModel()
    val meals=viewModel.mealsState.value
    LazyColumn(contentPadding = PaddingValues(8.dp)){
        items(meals){ meal->
            MealCategory(meal,navigationCallBack)
        }
    }
}

@Composable
fun MealCategory(meal:MealCategory,navigationCallBack:(String)->Unit){
    var isExpandable by remember{ mutableStateOf(false) }
    Card(colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
        ),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White)
            .clickable { navigationCallBack.invoke(meal.id.toString()) }) {
        Row(modifier = Modifier.animateContentSize()){
            AsyncImage(
                model = meal.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(128.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxSize(0.8f)
                .padding(16.dp)) {
                Text(
                    text =meal.name.toString(),
                    style = MaterialTheme.typography.titleLarge
                )

                Text(
                    modifier=Modifier.alpha(ContentAlpha.disabled),
                    text =meal.description.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines =if(isExpandable) 10 else 4
                )
            }
            Icon(imageVector = if(isExpandable)
                Icons.Filled.KeyboardArrowUp
                else Icons.Filled.KeyboardArrowDown, "Expand row icon",
                Modifier
                    .align(
                        if(isExpandable) Alignment.Bottom
                    else Alignment.CenterVertically)
                    .padding(12.dp)
                    .clickable { isExpandable=!isExpandable }
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealzAppTheme {
        MealsCategoryScreen({})
    }
}