package com.example.dacs3.ui.screens.home


import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dacs3.R
import com.example.dacs3.Screen
import com.example.dacs3.ui.screens.login.LoginViewModel
import kotlinx.coroutines.launch
import kotlin.math.log


@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel,
    mainViewModel: MainViewModel,
    loginViewModel: LoginViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val state = homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.getCategories(loginViewModel.getToken() ?: "")
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(16.dp))
                Text("Menu", modifier = Modifier.padding(16.dp), fontWeight = FontWeight.Bold)
                NavigationDrawerItem(
                    label = { Text("Trang chủ") },
                    selected = false,
                    onClick = { /* Xử lý chuyển trang nếu cần */ },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("Cài đặt") },
                    selected = false,
                    onClick = { /* ... */ },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text("Đăng xuất") },
                    selected = false,
                    onClick = {
                        // TODO: Xử lý đăng xuất
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeHeader(
                onMenuClick = {
                    scope.launch { drawerState.open() }
                },
                loginViewModel = loginViewModel,
                navController = navController
            )

            SliderComponent()

            FoodCategoryComponent(state.value)

            RecentSearches()

            Spacer(modifier = Modifier.weight(1f))
            BottomNavigationBar()
        }
    }
}


@Composable
fun HomeHeader(
    onMenuClick: () -> Unit,
    loginViewModel: LoginViewModel,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF5722))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(48.dp)
                    .padding(start = 8.dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.padding(end = 8.dp)
            )
            Button(
                onClick = {
                    loginViewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Đăng xuất", color = Color(0xFFFF5722))
            }
        }
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
