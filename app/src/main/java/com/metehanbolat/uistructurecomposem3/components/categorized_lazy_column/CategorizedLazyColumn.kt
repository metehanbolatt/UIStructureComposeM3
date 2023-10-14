package com.metehanbolat.uistructurecomposem3.components.categorized_lazy_column

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorizedHuman() {

    val namesList = names.map {
        Category(
            name = it.key.toString(),
            items = it.value
        )
    }
    Surface(Modifier.fillMaxSize()) {
        LazyColumn {
            namesList.forEach {
                stickyHeader {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray),
                        text = it.name)
                }
                items(it.items) {
                    Text(text = it)
                }
            }
        }
    }
}

data class Category(
    val name: String,
    val items: List<String>
)

@Preview(showBackground = true)
@Composable
fun CategorizedHumanPreview() {
    CategorizedHuman()
}