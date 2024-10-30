package com.example.listtest.navigation


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.listtest.ui.DetailScreen
import com.example.listtest.ui.InfiniteScrollGridScreen


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    val navControllerWithHistory = remember { NavControllerWithHistory(navController) }
    NavHost(
        navController = navControllerWithHistory.navController,
        startDestination = NavigationItem.Main.route,
        modifier = modifier
    ) {

        composable(NavigationItem.Main.route) {
            InfiniteScrollGridScreen(
                navController = navControllerWithHistory.navController,
            )
        }
        composable(
            NavigationItem.Detail.route + "/{url}",
            arguments = listOf(navArgument(name = "url") { type = NavType.StringType }),
            enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + scaleIn(
                    initialScale = 0.7f,
                    animationSpec = tween(300, easing = EaseIn)
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + scaleOut(
                    targetScale = 0.7f,
                    animationSpec = tween(300, easing = EaseOut)
                )
            }

        ) { backStackEntry ->
            val imageUrl = backStackEntry.arguments?.getString("url")

            if (imageUrl != null) {
                DetailScreen(
                    imageUrl = imageUrl,
                )
            }
        }
    }
}