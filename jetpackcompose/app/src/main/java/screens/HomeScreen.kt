@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetpackcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    // Giao diện chính của màn hình Home
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Trang chủ", fontSize = 20.sp) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Chọn chức năng:", fontSize = 22.sp)

            Spacer(modifier = Modifier.height(30.dp))

            // Nút chuyển sang TextScreen
            Button(
                onClick = { navController.navigate("text") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Xem Text")
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Nút chuyển sang ImageScreen
            Button(
                onClick = { navController.navigate("image") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Xem Hình ảnh")
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Nút chuyển sang TextFieldScreen
            Button(
                onClick = { navController.navigate("textfield") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nhập văn bản")
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Nút chuyển sang RowScreen
            Button(
                onClick = { navController.navigate("row") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Xem bố cục Row")
            }
        }
    }
}
