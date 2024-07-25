package com.jdw.simplewebscrapapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientListConverter {
//    @TypeConverter
//    fun fromIngredientList(ingredients: List<Ingredient>): String {
//        return Gson().toJson(ingredients)
//    }
//
//    @TypeConverter
//    fun toIngredientList(data: String): List<Ingredient> {
//        val listType = object : TypeToken<List<Ingredient>>() {}.type
//        return Gson().fromJson(data, listType)
//    }
}

class StringListConverter {
    @TypeConverter
    fun fromStringList(steps: List<String>): String {
        return Gson().toJson(steps)
    }

    @TypeConverter
    fun toStringList(data: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(data, listType)
    }
}