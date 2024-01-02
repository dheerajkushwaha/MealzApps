package com.example.mealzapp.ui.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.model.response.MealCategory

@Composable
fun MealDetailsScreen(meal: MealCategory?) {
    //val
    Surface(color=MaterialTheme.colorScheme.background) {
        Column {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Green
                        ),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = meal?.imageUrl).apply(block = fun ImageRequest.Builder.() {
                                        transformations(CircleCropTransformation())
                                    }).build()
                            ), contentDescription = null,
                            modifier = Modifier
                                .size(140.dp)
                                .padding(8.dp)
                        )
                    }
                    Text(
                        text = meal?.name.toString(),
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Text(
                text = meal?.description.toString(),
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .verticalScroll(rememberScrollState())
            )
            /*Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
                Text(text = "This is text element", modifier = Modifier.padding(32.dp))
            }*/

        }
    }

}

