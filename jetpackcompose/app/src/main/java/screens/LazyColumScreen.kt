@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.jetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LazyColScreen(navController: NavController) {  // ✅ Đổi tên để tránh trùng LazyColumn gốc
    val items = (1..100).map { "$it | The only way to do great work is to love what you do." }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LazyColumn", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3)
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(12.dp)
        ) {
            itemsIndexed(items) { _, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFB3E5FC), shape = MaterialTheme.shapes.medium)
                        .clickable { /* TODO: chuyển sang Detail nếu muốn */ }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item,
                        modifier = Modifier.weight(1f),
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
