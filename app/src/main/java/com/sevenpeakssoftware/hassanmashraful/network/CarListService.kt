package com.sevenpeakssoftware.hassanmashraful.network

import com.sevenpeakssoftware.hassanmashraful.network.model.CarListDto
import retrofit2.Response
import retrofit2.http.GET

interface CarListService {
    @GET("carlist")
    suspend fun fetchCarList(): Response<CarListDto>
}