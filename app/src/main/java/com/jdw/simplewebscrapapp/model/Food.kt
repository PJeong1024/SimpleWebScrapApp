package com.jdw.simplewebscrapapp.model

data class Food(
    var day: String = "",
    var menus: List<String> = emptyList(),
    var isHoliday: Boolean = false
)
