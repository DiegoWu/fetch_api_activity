package com.example.fetch_api_activity

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import kotlin.collections.*

class FetchRewardsViewModel : ViewModel() {
    private val api = ApiClient.api

    // allow UI to react to data changes by automatically re-rendering the UI when the data changes
    val items = mutableStateOf<List<Item>>(emptyList())
    var isClicked = false
    init {
        fetchData()
    }
    fun fetchData() {
        isClicked = false
        viewModelScope.launch { // coroutine scope, launch is used to start for async task
            val fetchedItems = api.getItems()
                .filter { !it.name.isNullOrBlank() }
                .sortedWith(compareBy({ it.listId }, { it.name }))

            items.value = fetchedItems
        }
    }
    fun clearItems() {
        viewModelScope.launch {
            isClicked = true
            items.value = emptyList()
        }
    }
}
