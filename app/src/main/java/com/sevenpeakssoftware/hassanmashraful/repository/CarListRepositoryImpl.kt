package com.sevenpeakssoftware.hassanmashraful.repository

import com.sevenpeakssoftware.hassanmashraful.datasource.CarListDao
import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDetails
import com.sevenpeakssoftware.hassanmashraful.network.CarListService
import com.sevenpeakssoftware.hassanmashraful.network.model.CarListDtoMapper
import com.sevenpeakssoftware.hassanmashraful.util.Status
import java.lang.Exception
import javax.inject.Inject

class CarListRepositoryImpl @Inject internal constructor(
    private val dao: CarListDao,
    private val carListService: CarListService,
    private val mapper: CarListDtoMapper
) : BaseRepository(), CarListRepository {

    override suspend fun fetchCarList(): Status<List<CarDetails>> {
        val response = try {
            apiCall {
                carListService.fetchCarList()
            }
        } catch (e: Exception) {
            return Status.Error("Something went wrong")
        }

        return if (response is Status.Success && response.data?.content != null) {
            Status.Success(mapper.mapToDomainModel(response.data.content!!))
        } else {
            Status.Error(response.message ?: "Something went wrong")
        }
    }

    override suspend fun insertCarList(carDetailsList: List<CarDetails>) {
        dao.saveCarList(mapper.mapFromDomainModel(carDetailsList))
    }

    override suspend fun getCarList(): Status<List<CarDetails>> {
        val response = try {
            dao.getCarList()
        } catch (e: Exception) {
            return Status.Error("Failed to load from database")
        }
        return Status.Success(mapper.mapToDomainModel(response ?: emptyList()))
    }
}