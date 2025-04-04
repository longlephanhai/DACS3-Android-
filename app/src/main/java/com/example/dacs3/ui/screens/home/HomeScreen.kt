package com.example.dacs3.ui.screens.home


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dacs3.MainViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dacs3.ui.screens.login.LoginViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    mainViewModel: MainViewModel,
    loginViewModel: LoginViewModel
) {
    val state = homeViewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        homeViewModel.getCategories(loginViewModel.getToken() ?: "")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        HomeHeader()

        // Slider
        SliderComponent()

        // Categories
        FoodCategoryComponent(state.value)

        // Recent Searches
        RecentSearches()

        // Bottom Navigation
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar()
    }
}

@Composable
fun HomeHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF5722))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Vị trí của bạn", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White)
    }
}


@Composable
fun RecentSearches() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Tìm kiếm gần đây", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("Nhà hàng gần bạn", fontSize = 14.sp, color = Color.Gray)
        }
        Text(
            "Xem tất cả",
            fontSize = 14.sp,
            color = Color(0xFFFF5722),
            modifier = Modifier.clickable {})
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {}
        )
    }
}
