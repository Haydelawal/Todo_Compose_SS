//package com.example.todo_compose_ss.navigation.destinations
//
//import androidx.compose.runtime.LaunchedEffect
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavType
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
//import com.example.todo_compose_ss.ui.screens.list.ListScreen
//import com.example.todo_compose_ss.ui.screens.splash.SplashScreen
//import com.example.todo_compose_ss.ui.viewmodel.MySharedViewModel
//import com.example.todo_compose_ss.utils.Constants
//import com.example.todo_compose_ss.utils.toAction
//
//fun NavGraphBuilder.splashComposable(
//    navigateToListScreen: () -> Unit
//) {
//    composable(
//        route = Constants.SPLASH_SCREEN,
//    )
//    {
//        SplashScreen(
//            navigateToListScreen = navigateToListScreen
//        )
//    }
//}
//
//
