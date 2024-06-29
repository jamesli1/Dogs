package com.example.dogs.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Screen {
    List,
    Details
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.List.name,
) {
    val viewModel: DogViewModel = hiltViewModel()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = Screen.List.name) {
            DogListScreen(
                onItemClick = {
                    viewModel.getDetailsById(it)
                    navController.navigate(Screen.Details.name)
                })
        }

        composable(route = Screen.Details.name) {
            DogDetailsScreen(viewModel)
        }
    }
}