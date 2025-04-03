package com.example.dacs3

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dacs3.ui.screens.home.HomeScreen
import com.example.dacs3.ui.screens.home.HomeViewModel
import com.example.dacs3.ui.screens.login.LoginScreen
import com.example.dacs3.ui.screens.login.LoginViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = hiltViewModel()
    val mainState = mainViewModel.uiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(mainState.value.error) {
        if (mainState.value.error != "") {
            Toast.makeText(context, mainState.value.error, Toast.LENGTH_LONG).show()
            mainViewModel.setError("")
        }
    }
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(navController, loginViewModel = hiltViewModel<LoginViewModel>(), mainViewModel)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController, viewModel = hiltViewModel<HomeViewModel>(), mainViewModel)
        }
    }
}