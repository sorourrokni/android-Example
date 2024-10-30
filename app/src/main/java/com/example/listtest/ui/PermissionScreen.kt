package com.example.listtest.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.listtest.activity.MainActivity
import com.example.listtest.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(
    locationPermissionState: PermissionState,
    mainActivity: MainActivity,
) {
    var flag by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.gps), contentDescription = "gps img")
        Text(
            modifier = Modifier.padding(4.dp),
            text = "ENABLE YOUR LOCATION",
            style = TextStyle(fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        )
        Text(
            text = "This app needs location permission !",
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = { locationPermissionState.launchPermissionRequest()
                    flag = "location permission granted "
                          },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("GRANT PERMISSION")
            }
            Button(
                onClick = {
                    mainActivity.finishAndRemoveTask()
                }, colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary)
            ) {
                Text("NOT NOW")
            }
        }
    }
}
