package com.jdw.simplewebscrapapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jdw.simplewebscrapapp.R
import com.jdw.simplewebscrapapp.components.RecipeAppBar
import com.jdw.simplewebscrapapp.screens.viewmodel.MainViewModel
import com.jdw.simplewebscrapapp.utils.BottomNaviBarScreen
import com.jdw.simplewebscrapapp.utils.Constants

@Composable
fun MainScreen(
    navController: NavController = NavController(LocalContext.current),
    viewModel: MainViewModel = hiltViewModel()
) {

    val currentScreen = remember { mutableStateOf(BottomNaviBarScreen.MonthlyMenus) }

    Scaffold(
        topBar = {
            RecipeAppBar(
                title = stringResource(R.string.app_name),
                navController = navController,
                showProfile = false
            )
        },
        bottomBar = {
            BottomNavigationBar(
                items = Constants.items,
                currentScreen = currentScreen,
                onItemSelected = { screen ->
                    currentScreen.value = screen
//                    navController.navigate(screen.route)
                }
            )
        }
    ) { paddingValues ->
        CurrentScreen(
            screen = currentScreen.value,
            paddingValues = paddingValues,
            navController,
            viewModel
        )
    }
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNaviBarScreen>,
    currentScreen: MutableState<BottomNaviBarScreen>,
    onItemSelected: (BottomNaviBarScreen) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth().background(Color(0xFFB3E5FC)),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEach { screen ->
            BottomNavigationItem(
                screen = screen,
                isSelected = currentScreen.value == screen,
                onItemSelected = { onItemSelected(screen) }
            )
        }
    }
}

@Composable
fun BottomNavigationItem(
    screen: BottomNaviBarScreen,
    isSelected: Boolean,
    onItemSelected: () -> Unit
) {
    val iconColor = if (isSelected) Color(0xFF01579B) else Color(0xFF9DB3B6)
    val textColor = if (isSelected) Color(0xFF01579B) else Color(0xFF9DB3B6)

    Button(
        onClick = onItemSelected,
        modifier = Modifier
            .width(120.dp)
            .height(80.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = screen.icon, tint = iconColor, contentDescription = "Icon")
            Text(
                text = screen.label,
                color = textColor,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CurrentScreen(
    screen: BottomNaviBarScreen,
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: MainViewModel
) {
    when (screen) {
        BottomNaviBarScreen.MonthlyMenus -> MonthlyMenusScreen(
            navController = navController,
            viewModel,
            paddingValues = paddingValues
        )

        BottomNaviBarScreen.SecondaryTap -> SecondScreen(
            navController = navController,
            viewModel,
            paddingValues = paddingValues
        )

        BottomNaviBarScreen.ThirdTap -> ThirdScreen(
            navController = navController,
            viewModel,
            paddingValues = paddingValues
        )
    }
}