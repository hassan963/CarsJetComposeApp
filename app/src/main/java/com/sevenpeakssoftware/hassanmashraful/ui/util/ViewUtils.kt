package com.sevenpeakssoftware.hassanmashraful.ui.util

import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.lang.Math.PI
import kotlin.math.pow
import kotlin.math.sqrt

@Composable
fun ShowSystemBar(isVisible: Boolean) {
    rememberSystemUiController().apply {
        this.isSystemBarsVisible = isVisible
        this.isNavigationBarVisible = isVisible
        this.isStatusBarVisible = isVisible
    }
}

@Composable
fun StatusBarColor(color: Color) {
    rememberSystemUiController().apply {
        this.setStatusBarColor(color)
    }
}

@Composable
fun SnackbarScreen(message: String) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    SnackbarHost(hostState = snackbarHostState)
}

fun Modifier.gradientBackground(colors: List<Color>, angle: Float) = this.then(
    Modifier.drawBehind {

        val angleRad = angle / 180f * PI

        val x = kotlin.math.cos(angleRad).toFloat()

        val y = kotlin.math.sin(angleRad).toFloat()

        val radius = sqrt(size.width.pow(2) + size.height.pow(2)) / 2f
        val offset = center + Offset(x * radius, y * radius)

        val exactOffset = Offset(
            x = kotlin.math.min(offset.x.coerceAtLeast(0f), size.width),
            y = size.height - kotlin.math.min(offset.y.coerceAtLeast(0f), size.height)
        )

        drawRect(
            brush = Brush.linearGradient(
                colors = colors,
                start = Offset(size.width, size.height) - exactOffset,
                end = exactOffset
            ),
            size = size
        )
    }
)