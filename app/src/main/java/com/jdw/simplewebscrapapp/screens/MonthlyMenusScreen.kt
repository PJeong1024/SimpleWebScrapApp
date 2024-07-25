package com.jdw.simplewebscrapapp.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jdw.simplewebscrapapp.components.RoundedDescTextBox
import com.jdw.simplewebscrapapp.components.RoundedTitleTextBox
import com.jdw.simplewebscrapapp.model.Food
import com.jdw.simplewebscrapapp.screens.viewmodel.MainViewModel

@Composable
fun MonthlyMenusScreen(
    navController: NavController,
    viewModel: MainViewModel,
    paddingValues: PaddingValues
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        TestContents(navController, viewModel)
    }
}


@Composable
fun TestContents(navController: NavController, viewModel: MainViewModel) {
    val context = LocalContext.current
    val lunchMenus = viewModel.foodElements.collectAsState().value
    val loadingState = viewModel.uriData.collectAsState().value.loading
    val isLoading = remember { mutableStateOf(true) }

    if (loadingState == false) isLoading.value = false
    Log.d("TestContents", "lunchMenus.isNullOrEmpty(): ${lunchMenus.isEmpty()}")

    if (isLoading.value) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Loading...")
            LinearProgressIndicator()
        }
    } else {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (lunchMenus.isEmpty()) {
                Text(text = "No data")
            } else {
                LazyColumn(modifier = Modifier.padding(10.dp)) {
                    items(lunchMenus) { food ->
                        FoodCard(food = food, viewModel = viewModel, context = context)
                        Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FoodCard(
    food: Food,
    viewModel: MainViewModel,
    context: Context,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            RoundedTitleTextBox(width = 300.dp, text = food.day)
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )

        if (food.isHoliday) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.LightGray
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = "오늘은 휴일입니다",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        } else {
            val menus = food.menus
            Column(modifier = Modifier.padding(10.dp)) {
                menus.forEach { menu ->
                    RoundedDescTextBox(text = menu)
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}

