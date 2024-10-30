package com.example.listtest.navigation

sealed class NavigationItem(var route: String) {
    data object Main :
        NavigationItem(
            route = "main"
        )
    data object Detail :
        NavigationItem(
            route = "detail"
        )
}