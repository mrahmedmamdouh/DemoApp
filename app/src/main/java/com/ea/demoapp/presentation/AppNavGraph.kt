package com.ea.demoapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ea.demoapp.presentation.view.DetailScreen
import com.ea.demoapp.presentation.view.GreetingsScreen
import com.ea.demoapp.presentation.view.LoginScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Greetings : Screen("greetings_screen/{username}") {
        fun createRoute(username: String) = "greetings_screen/$username"
    }

    object Details : Screen("details_screen/{medicineName}/{dose}/{strength}") {
        fun createRoute(name: String, dose: String, strength: String): String {
            val encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString())
            val encodedDose = URLEncoder.encode(dose, StandardCharsets.UTF_8.toString())
            val encodedStrength = URLEncoder.encode(strength, StandardCharsets.UTF_8.toString())
            return "details_screen/$encodedName/${encodedDose.ifEmpty { "-" }}/${encodedStrength.ifEmpty { "-" }}"
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.Greetings.route,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            GreetingsScreen(
                navController = navController,
                username = backStackEntry.arguments?.getString("username") ?: ""
            )
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("medicineName") { type = NavType.StringType },
                navArgument("dose") { type = NavType.StringType },
                navArgument("strength") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val medicineName = URLDecoder.decode(
                backStackEntry.arguments?.getString("medicineName") ?: "",
                StandardCharsets.UTF_8.toString()
            )
            val dose = URLDecoder.decode(
                backStackEntry.arguments?.getString("dose") ?: "",
                StandardCharsets.UTF_8.toString()
            )
            val strength = URLDecoder.decode(
                backStackEntry.arguments?.getString("strength") ?: "",
                StandardCharsets.UTF_8.toString()
            )

            DetailScreen(
                navController = navController,
                medicineName = medicineName,
                dose = dose,
                strength = strength
            )
        }

    }
}
