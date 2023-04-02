package com.sevenpeakssoftware.hassanmashraful.ui.component

import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDetails
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.sevenpeakssoftware.hassanmashraful.ui.util.gradientBackground
import com.sevenpeakssoftware.hassanmashraful.ui.theme.*
import com.sevenpeakssoftware.hassanmashraful.util.*
import com.sevenpeakssoftware.hassanmashraful.R

@Composable
fun CarCard(carDetails: CarDetails, is24HourFormat: Boolean) {

    val formattedDate = remember {
        mutableStateOf(
            if (carDetails.dateTime.isNullOrBlank()) {
                ""
            } else {
                if (isCurrentYear(carDetails.dateTime!!)) {
                    formatDateWithPattern(
                        date = carDetails.dateTime!!,
                        expectedFormat = DATE_MONTH_TIME_COMMA_SEPARATED_FORMAT
                    )
                } else {
                    formatDateWithPattern(
                        date = carDetails.dateTime!!,
                        expectedFormat = DATE_MONTH_YEAR_TIME_COMMA_SEPARATED_FORMAT
                    )
                }
            }
        )
    }

    val formattedTime = remember {
        mutableStateOf(
            if (carDetails.dateTime.isNullOrBlank()) {
                ""
            } else {
                if (is24HourFormat) {
                    formatDateWithPattern(
                        date = carDetails.dateTime!!,
                        expectedFormat = TIME_FORMAT_TWENTY_FOUR_HOUR
                    )
                } else {
                    formatDateWithPattern(
                        date = carDetails.dateTime!!,
                        expectedFormat = TIME_FORMAT_AM_PM
                    )
                }
            }
        )
    }

    val imageUrl = remember {
        mutableStateOf(
            if (containsLink(carDetails.image)) {
                carDetails.image
            } else {
                BASE_URL + "/" + carDetails.image
            }
        )
    }

    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.fillMaxSize(),
        backgroundColor = black
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(276.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                AsyncImage(
                    model = imageUrl.value,
                    contentDescription = "car_image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.ic_baseline_error_outline_24)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .gradientBackground(
                            listOf(
                                Color.Transparent, black
                            ),
                            270f
                        )
                )

                Text(
                    text = carDetails.title,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 40.dp
                        )
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h6,
                    color = white
                )
            }

            if (formattedDate.value.isNotEmpty() && formattedTime.value.isNotEmpty()) {
                Text(
                    text = formattedDate.value + ", " + formattedTime.value,
                    modifier = Modifier
                        .padding(
                            top = 8.dp,
                            start = 16.dp,
                            end = 40.dp
                        )
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.body2,
                    color = grey
                )
            }

            carDetails.ingress?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(
                            top = 14.dp,
                            start = 16.dp,
                            end = 40.dp,
                            bottom = 24.dp
                        )
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.body2,
                    color = white
                )
            }
        }
    }
}