package com.example.fetch_api_activity

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Collections.emptyList
import kotlin.collections.*

class FetchRewardsViewModel : ViewModel() {
    private val api = ApiClient.api

    val items = mutableStateOf<List<Item>>(emptyList())

    fun fetchData() {
        viewModelScope.launch {
            val fetchedItems = api.getItems()
                .filter { !it.name.isNullOrBlank() }
                .sortedWith(compareBy({ it.listId }, { it.name }))

            items.value = fetchedItems
        }
    }
}
