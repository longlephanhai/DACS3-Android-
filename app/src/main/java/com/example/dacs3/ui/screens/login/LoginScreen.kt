package com.example.dacs3.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dacs3.MainViewModel
import com.example.dacs3.Screen
import com.example.dacs3.common.enum.LoadStatus

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel,
    mainViewModel: MainViewModel
) {
    val state = loginViewModel.uiState.collectAsState()
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (state.value.status is LoadStatus.Loading) {
            CircularProgressIndicator()
        } else if (state.value.status is LoadStatus.Success) {
            LaunchedEffect(state.value.status) {
                if (state.value.status is LoadStatus.Success) {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            }

        } else {
            if (state.value.status is LoadStatus.Error) {
                mainViewModel.setError(state.value.status.description)
                loginViewModel.reset()
            }
            OutlinedTextField(
                value = state.value.email, onValueChange = {
                    loginViewModel.updateEmail(it)
                }, modifier = Modifier.padding(16.dp),
                label = { Text("Email") }
            )
            OutlinedTextField(
                value = state.value.password, onValueChange = {
                    loginViewModel.updatePassword(it)
                }, modifier = Modifier.padding(16.dp),
                label = { Text("Password") }
            )
            ElevatedButton(
                onClick = { loginViewModel.login() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Login")
            }
        }
    }

}