package com.example.topappbarsearch.presentation

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    SearchListScreen()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchListScreen() {

    val allItems = listOf(
        "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb",
        "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop",
        "Marshmallow", "Nougat", "Oreo Cheesecake", "Pudding",
        "Raspberry Tart", "Tiramisu", "Vanilla Panna Cotta"
    )

    var isSearching by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    // Filter list based on search
    val filteredItems = if (searchText.isBlank()) {
        allItems
    } else {
        allItems.filter { it.contains(searchText, ignoreCase = true) }
    }

    val context = LocalContext.current
    Column {
        TopAppBar(
            navigationIcon = {
                IconButton(onClick = {
                   // onMenuClick()
                    Toast.makeText(context,"Menu Clicked!", Toast.LENGTH_SHORT).show()
                }) {
                    if(isSearching){
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back out of searching",
                            modifier = Modifier
                                .clickable { isSearching = false }
                                .size(32.dp),
                            tint = Color.Blue
                        )
                    }else{
                        Icon(
                            modifier = Modifier
                                .size(48.dp),
                            imageVector = Icons.Rounded.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            },
            title = {
                if (isSearching) {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        placeholder = { Text("Search...") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                } else {
                    Text("Desserts", fontStyle = FontStyle.Italic, fontFamily = FontFamily.Serif)
                }
            },
            actions = {
                if (isSearching) {
//                    if(searchText.isNotEmpty()){
                        IconButton(onClick = {
                            // isSearching = false
                            searchText = ""
                        }) {
                            Icon(Icons.Default.Close, contentDescription = "Close Search")
                        }
//                    }

                } else {
                    IconButton(onClick = { isSearching = true }) {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    }
                }
            }
        )

        val context = LocalContext.current
        if (isSearching){
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(filteredItems) { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .clickable{
                                Toast.makeText(context, "$item", Toast.LENGTH_SHORT).show()
                                isSearching = false
                            }
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }
            }
    }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}