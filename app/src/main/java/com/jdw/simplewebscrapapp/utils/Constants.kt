package com.jdw.simplewebscrapapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

object Constants {
    val items = listOf(
        BottomNaviBarScreen.MonthlyMenus,
        BottomNaviBarScreen.SecondaryTap,
        BottomNaviBarScreen.ThirdTap,
    )

    const val SUNGRI_URL_MONTH = "http://sungri.icees.kr/foodlist.do?m=020605&s=sungri"

}

enum class BottomNaviBarScreen(val label: String, val icon: ImageVector, val route: String) {
    MonthlyMenus("Monthly Menus", Icons.Filled.Menu, "monthlymenus"),
    SecondaryTap("Second Tap", Icons.Filled.Menu, "secondtap"),
    ThirdTap("Third Tap", Icons.Filled.Menu, "thirdtap"),
}