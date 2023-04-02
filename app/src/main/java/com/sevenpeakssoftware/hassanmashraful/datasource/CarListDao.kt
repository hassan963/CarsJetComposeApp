package com.sevenpeakssoftware.hassanmashraful.datasource

import com.sevenpeakssoftware.hassanmashraful.network.model.CarDetailsDto
import io.realm.Realm
import javax.inject.Inject

class CarListDao @Inject internal constructor(
    private val db: Realm
) {
    fun saveCarList(list: List<CarDetailsDto>) {
        db.executeTransaction {
            it.insertOrUpdate(list)
        }
    }

    fun getCarList(): List<CarDetailsDto>? {
        var carList: List<CarDetailsDto>? = null
        db.executeTransaction {
            val list = it.where(CarDetailsDto::class.java).findAll()
            carList = it.copyFromRealm(list)
        }
        return carList
    }
}