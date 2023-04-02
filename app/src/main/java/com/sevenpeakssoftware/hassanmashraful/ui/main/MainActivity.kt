package com.sevenpeakssoftware.hassanmashraful.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.sevenpeakssoftware.hassanmashraful.navigation.Screens
import com.sevenpeakssoftware.hassanmashraful.navigation.setupNavController
import com.sevenpeakssoftware.hassanmashraful.ui.theme.CarsAppTheme
import com.sevenpeakssoftware.hassanmashraful.ui.util.ShowSystemBar
import com.sevenpeakssoftware.hassanmashraful.ui.util.StatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CarsAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    StatusBarColor(Color.Black)

                    ShowSystemBar(isVisible = true)

                    setupNavController(rememberNavController(), Screens.carListScreen.route)
                }
            }
        }
    }
}