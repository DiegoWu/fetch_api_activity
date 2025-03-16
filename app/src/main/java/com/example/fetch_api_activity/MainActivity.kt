package com.example.fetch_api_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchRewardsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    ItemList()
                }
            }
        }
    }
    @Composable
    fun ItemList() {
        val fetchRewardsViewModel: FetchRewardsViewModel = viewModel()
        val items = fetchRewardsViewModel.items.value

        LazyColumn {
            items(items) { item ->
                Column(modifier = Modifier.padding(8.dp)) {
                    BasicText(
                        text = "Item Name: ${item.name}",
                        style = TextStyle(fontSize = 20.sp)
                    )
                    BasicText(
                        text = "List ID: ${item.listId}",
                        style = TextStyle(fontSize = 16.sp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        fetchRewardsViewModel.fetchData()
    }
}
