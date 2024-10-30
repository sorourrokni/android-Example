package com.example.listtest.worker

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.listtest.worker.LocationWorker
import java.util.concurrent.TimeUnit

fun startLocationWorker(context: Context) {
    val locationWorkRequest = PeriodicWorkRequestBuilder<LocationWorker>(10, TimeUnit.SECONDS)
        .build()
    WorkManager.getInstance(context).enqueue(locationWorkRequest)
}
