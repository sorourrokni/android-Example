package com.example.listtest.util

import android.content.Context
import android.content.Intent
import android.app.PendingIntent
import android.os.Build
import com.example.listtest.activity.MainActivity

fun bringAppToForeground(context: Context) {
    val intent = Intent(context, MainActivity::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        pendingIntent.send()
    } else {
        context.startActivity(intent)
    }
}
