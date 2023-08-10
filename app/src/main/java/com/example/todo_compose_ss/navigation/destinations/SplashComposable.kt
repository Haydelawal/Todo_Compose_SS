package com.example.todo_compose_ss.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.example.todo_compose_ss.ui.screens.splash.SplashScreen
import com.example.todo_compose_ss.utils.Constants
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN,
        exitTransition = {_, _ ->
             slideOutVertically (
                 targetOffsetY = { fullHeight -> -fullHeight },
                 animationSpec = tween(300)
             )
        }
    )
    {
        SplashScreen(
            navigateToListScreen = navigateToListScreen
        )
    }
}


