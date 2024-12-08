package com.ea.demoapp.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun MyApp() {
    val navController: NavHostController = rememberNavController()
    AppNavGraph(navController)
}
