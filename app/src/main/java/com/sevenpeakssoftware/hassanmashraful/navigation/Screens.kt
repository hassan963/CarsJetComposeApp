package com.sevenpeakssoftware.hassanmashraful.navigation

sealed class Screens(val route: String) {
    object carListScreen: Screens("mainScreen")
}