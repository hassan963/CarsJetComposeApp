package com.sevenpeakssoftware.hassanmashraful.repository

import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDetails
import com.sevenpeakssoftware.hassanmashraful.util.Status

interface CarListRepository {
    suspend fun fetchCarList(): Status<List<CarDetails>>

    suspend fun insertCarList(carDetailsList: List<CarDetails>)

    suspend fun getCarList(): Status<List<CarDetails>>
}