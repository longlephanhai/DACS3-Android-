package com.example.dacs3.ui.screens.home


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.dacs3.MainViewModel


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    mainViewModel: MainViewModel
) {
    Text("home")
}