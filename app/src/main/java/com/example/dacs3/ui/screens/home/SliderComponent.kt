package com.example.dacs3.ui.screens.home

import androidx.compose.runtime.Composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dacs3.R

@Composable
fun SliderComponent() {
    val images = listOf(
        R.drawable.slider1,
        R.drawable.slider1,
        R.drawable.slider1,
        R.drawable.slider1,
        R.drawable.slider1
    )

    val pagerState = rememberPagerState(pageCount = { images.size })

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Slider chính
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) { page ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)  // Thêm padding ngoài Card nếu cần
                    .shadow(4.dp, shape = MaterialTheme.shapes.medium), // Thêm bóng đổ cho Card
                shape = MaterialTheme.shapes.medium,
            ) {
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "Slider Image",
                    modifier = Modifier
                        .fillMaxSize() // Ảnh sẽ chiếm toàn bộ Card
                        .clip(MaterialTheme.shapes.medium) // Cắt ảnh theo góc của Card
                )
            }

        }

        Spacer(modifier = Modifier.height(8.dp))


    }
}
