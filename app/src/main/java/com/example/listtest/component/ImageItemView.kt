package com.example.listtest.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.listtest.navigation.NavigationItem
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ImageItemView(url: String, navController: NavHostController) {

    val painter = rememberAsyncImagePainter(model = url)
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())

    Card(modifier = Modifier.clickable {
        navController.navigate(NavigationItem.Detail.route + "/$encodedUrl")
    }) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
        )
    }
}