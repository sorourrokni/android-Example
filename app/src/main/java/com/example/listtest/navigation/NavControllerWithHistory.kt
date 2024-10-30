package com.example.listtest.navigation

import androidx.navigation.NavHostController

class NavControllerWithHistory(
    val navController: NavHostController
) {
    private val history = mutableListOf<String>()

    fun navigate(route: String) {
        history.add(route)
        navController.navigate(route)
    }

    fun popBackStack() {
        if (history.isNotEmpty()) {
            history.removeAt(history.lastIndex)
        }
        navController.popBackStack()
    }

    fun getPreviousRoute(): String? {
        return if (history.size >= 2) {
            history[history.lastIndex - 1]
        } else {
            null
        }
    }
}