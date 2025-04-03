package com.example.dacs3.ui.screens.start

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dacs3.MainViewModel
import com.example.dacs3.R
import com.example.dacs3.Screen

@Composable
fun StartScreen(
    navController: NavController,
    startViewModel: StartViewModel,
    mainViewModel: MainViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Nền trắng toàn màn hình
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo hoặc tên ứng dụng
            Text(
                text = "Welcome to Long-Food",
                fontSize = 30.sp,
                color = Color(0xFFFF5722),
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Nền tảng giao đồ ăn trực tuyến hàng đầu Việt Nam",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút đăng nhập Facebook
            SocialLoginButton(
                text = "Đăng nhập với Facebook",
                icon = R.drawable.facebook,
                backgroundColor = Color(0xFF1877F2),
                onClick = { /* Xử lý đăng nhập Facebook */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút đăng nhập Google
            SocialLoginButton(
                text = "Đăng nhập với Google",
                icon = R.drawable.google,
                backgroundColor = Color.White,
                textColor = Color.Black,
                borderColor = Color.LightGray,
                onClick = { /* Xử lý đăng nhập Google */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nút đăng nhập với Email
            OutlinedButton(
                onClick = { navController.navigate(Screen.Login.route)},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                border = BorderStroke(1.dp, Color.Gray)
            ) {
                Text(text = "Đăng nhập với email", color = Color.Black, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Điều hướng đăng ký
            Row {
                Text(text = "Chưa có tài khoản?", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Đăng ký ngay",
                    color = Color(0xFFFF5722),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable { /* Chuyển sang màn hình đăng ký */ }
                )
            }
        }
    }
}

@Composable
fun SocialLoginButton(
    text: String,
    icon: Int,
    backgroundColor: Color,
    textColor: Color = Color.White,
    borderColor: Color? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .then(if (borderColor != null) Modifier.border(1.dp, borderColor, RoundedCornerShape(50.dp)) else Modifier),
        shape = RoundedCornerShape(50.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, color = textColor, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
