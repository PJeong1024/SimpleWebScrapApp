package com.jdw.simplewebscrapapp.screens.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdw.simplewebscrapapp.data.DataOrException
import com.jdw.simplewebscrapapp.model.Food
import com.jdw.simplewebscrapapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

) : ViewModel(){

    private val _uriData: MutableStateFlow<DataOrException<Elements, Boolean, Exception>> =
        MutableStateFlow(
            DataOrException(Elements(), true, Exception(""))
        )
    val uriData = _uriData.asStateFlow()

    private val _foodElements = MutableStateFlow<List<Food>>(emptyList())
    val foodElements = _foodElements.asStateFlow()

    init {
        getDataFromUri()
    }

    private fun getDataFromUri() {
        viewModelScope.launch(Dispatchers.IO) {
            _uriData.value.loading = true
            try {
                val url = Constants.SUNGRI_URL_MONTH
//                val doc: Document = withContext(Dispatchers.IO) {
//                    Jsoup.connect(url).get()
//                }
                val doc: Document = Jsoup.connect(url).get()
                _uriData.value.data =
                    doc.select("div.tb_base_box.tm_box").select("tbody").select("tr")

                val foodList = mutableListOf<Food>()
                for (tr in _uriData.value.data!!) {
                    val food = Food()
                    food.day = tr.select("th span").text()
                    if (tr.select("td ul").isEmpty()) {
                        food.isHoliday = true
                    }
                    val menus = tr.select("td ul").html().split("<br>").map {
                        it.trim()
                    }
                    food.menus = menus
                    foodList.add(food)
                }
                _foodElements.value = foodList
                _uriData.value.loading = false
            } catch (e: IOException) {
//                withContext(Dispatchers.Main) {
                _uriData.value.loading = false
                _uriData.value.e = e
//                }
                Log.d("HomeScreenViewModel", "error: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}