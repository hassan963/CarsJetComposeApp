package com.sevenpeakssoftware.hassanmashraful.ui.carlist

import android.os.Build
import android.text.format.DateFormat
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sevenpeakssoftware.hassanmashraful.R
import com.sevenpeakssoftware.hassanmashraful.ui.component.CarCard
import com.sevenpeakssoftware.hassanmashraful.ui.util.CircularIndeterminateProgressBar
import com.sevenpeakssoftware.hassanmashraful.ui.util.SnackbarScreen
import com.sevenpeakssoftware.hassanmashraful.viewmodel.CarListViewModel

@Composable
fun CarListScreen(viewModel: CarListViewModel) {
    val carList by viewModel.carList.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()

    val errorMessage by viewModel.errorMsg.collectAsState()

    val is24HourFormat = DateFormat.is24HourFormat(LocalContext.current)

    Column {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            TopAppBar(
                elevation = 2.dp,
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter,
        ) {
            LazyColumn {
                itemsIndexed(
                    items = carList
                ) { _, car ->
                    CarCard(carDetails = car, is24HourFormat)
                }
            }

            if (carList.isEmpty() && errorMessage.isNotEmpty()) {
                SnackbarScreen(message = errorMessage)
            }

            CircularIndeterminateProgressBar(isDisplayed = isLoading)
        }
    }
}