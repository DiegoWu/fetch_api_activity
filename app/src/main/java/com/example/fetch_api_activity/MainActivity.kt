package com.example.fetch_api_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fetchrewards.ui.theme.FetchRewardsTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // set jetpack compose UI
            FetchRewardsTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
    @Composable
    fun MultiSelectableItemList() {
        val selectedItems = remember { mutableStateOf(mutableSetOf<Item>()) }
        val fetchRewardsViewModel: FetchRewardsViewModel = viewModel() // init ViewModel
        val items = fetchRewardsViewModel.items.value // get current item states
        val isClicked = fetchRewardsViewModel.isClicked
        // UI elements
        Column {
            val text = if (isClicked) "show Items" else "clear Items"
            if (!isClicked) {
                Button(onClick = { fetchRewardsViewModel.clearItems() }) {
                    Text(text)
                }
            } else {
                Button(onClick = { fetchRewardsViewModel.fetchData() }) {
                    Text(text)
                }
            }
            LazyColumn {
                items(items) { item ->
                    SelectableItem(
                        item = item,
                        isSelected = selectedItems.value.contains(item),
                        onSelect = {
                            val newSelection = selectedItems.value.toMutableSet()
                            if (!newSelection.add(it)) newSelection.remove(it) // Toggle selection
                            selectedItems.value = newSelection
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun SelectableItem(item: Item, isSelected: Boolean, onSelect: (Item) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onSelect(item) }  // Click to select
                .background(if (isSelected) Color.LightGray else Color.Transparent) // Change color when selected
                .padding(8.dp)
        ) {
            Text(text = "Item Name: ${item.name}", fontSize = 20.sp)
            Text(text = "List ID: ${item.listId}", fontSize = 16.sp)
        }
    }

    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp), // Spacing between elements
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Fetch Rewards Items",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            // Item list (existing Composable)
            MultiSelectableItemList()
        }
    }
}
