package com.example.listtest.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.core.app.ActivityCompat
import com.example.listtest.component.SwitchItem

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SettingsScreen(
    onDismissRequest: () -> Unit,
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        content = {
            Card {
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = RectangleShape
                        )
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    SwitchItem(
                        title = "WiFi",
                        isChecked = isWiFiEnabled(context),
                        onCheckedClick = {
                            onDismissRequest()
                            context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                        }
                    )
                    SwitchItem(
                        title = "Mobile Data",
                        isChecked = isMobileDataEnabled(context),
                        onCheckedClick = {
                            onDismissRequest()
                            context.startActivity(Intent(Settings.ACTION_DATA_USAGE_SETTINGS))
                        }
                    )

                    SwitchItem(
                        title = "GPS",
                        isChecked = isLocationEnabled(context),
                        onCheckedClick = {
                            onDismissRequest()
                            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                        }
                    )

                    SwitchItem(
                        title = "Flight Mode",
                        isChecked = isAirplaneModeEnabled(context),
                        onCheckedClick = {
                            onDismissRequest()
                            context.startActivity(Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS))
                        }
                    )

                    SwitchItem(
                        title = "Power Saving",
                        isChecked = isBatterySaverEnabled(context),
                        onCheckedClick = {
                            onDismissRequest()
                            context.startActivity(Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS))
                        }
                    )

                    SwitchItem(
                        title = "Storage Access",
                        isChecked = isStoragePermissionGranted(context),
                        onCheckedClick = {
                            if (!isStoragePermissionGranted(context)) {
                                val intent =
                                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                        data = Uri.parse("package:${context.packageName}")
                                    }
                                context.startActivity(intent)
                            }
                        }
                    )
                    SwitchItem(
                        title = "Auto Time",
                        isChecked = isAutoTimeEnabled(context),
                        onCheckedClick = {
                            context.startActivity(Intent(Settings.ACTION_DATE_SETTINGS))
                        }
                    )
                }
            }
        }

    )
}

fun isWiFiEnabled(context: Context): Boolean {
    return Settings.System.getInt(context.contentResolver, Settings.Global.WIFI_ON, 0) != 0
}

fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
        LocationManager.NETWORK_PROVIDER
    )
}

fun isAirplaneModeEnabled(context: Context): Boolean {
    return Settings.System.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
}

@SuppressLint("ServiceCast")
fun isMobileDataEnabled(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val networkCapabilities =
        connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
}

fun isBatterySaverEnabled(context: Context): Boolean {
    return Settings.Global.getInt(context.contentResolver, "low_power", 0) == 1
}

fun isStoragePermissionGranted(context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(
        context, Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}

fun isAutoTimeEnabled(context: Context): Boolean {
    return Settings.Global.getInt(context.contentResolver, Settings.Global.AUTO_TIME, 0) != 0
}
