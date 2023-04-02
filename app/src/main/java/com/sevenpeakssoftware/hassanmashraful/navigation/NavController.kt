package com.sevenpeakssoftware.hassanmashraful.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sevenpeakssoftware.hassanmashraful.ui.carlist.CarListScreen
import com.sevenpeakssoftware.hassanmashraful.viewmodel.CarListViewModel

@Composable
fun setupNavController(controller: NavHostController, startDestination: String) {
    NavHost(navController = controller, startDestination = startDestination) {
        composable(route = Screens.carListScreen.route) {
            val viewModel = hiltViewModel<CarListViewModel>()
            CarListScreen(viewModel)
        }
    }
}