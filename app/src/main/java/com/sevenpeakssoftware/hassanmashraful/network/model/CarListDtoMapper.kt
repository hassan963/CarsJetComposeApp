package com.sevenpeakssoftware.hassanmashraful.network.model

import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDetails
import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDescription
import com.sevenpeakssoftware.hassanmashraful.domain.util.DomainMapper
import io.realm.RealmList

class CarListDtoMapper : DomainMapper<List<CarDetailsDto>, List<CarDetails>> {
    override fun mapToDomainModel(model: List<CarDetailsDto>): List<CarDetails> {
        val listOfCarDetails = mutableListOf<CarDetails>()

        model.forEach { car ->
            val id = car.id
            val title = car.title
            val dateTime = car.dateTime
            val carDetails = car.content
            val ingress = car.ingress
            val image = car.image

            val list = carDetails?.map {
                CarDescription(
                    it.subject,
                    it.description
                )
            }

            if (id != null && title != null && image != null) {
                val content = CarDetails(
                    id,
                    title,
                    dateTime,
                    list,
                    ingress,
                    image
                )

                listOfCarDetails.add(content)
            }
        }

        return listOfCarDetails
    }

    override fun mapFromDomainModel(domainModel: List<CarDetails>): MutableList<CarDetailsDto> {
        val listOfCars = mutableListOf<CarDetailsDto>()

        domainModel.forEach { car ->
            val id = car.id
            val title = car.title
            val dateTime = car.dateTime
            val carDetails = car.content
            val ingress = car.ingress
            val image = car.image

            val list = carDetails?.map {
                CarDescriptionDto(
                    it.subject,
                    it.description
                )
            }

            val detailList = RealmList<CarDescriptionDto>()
            list?.let { detailList.addAll(it) }

            val content = CarDetailsDto(
                id,
                title,
                dateTime,
                detailList,
                ingress,
                image
            )

            listOfCars.add(content)
        }

        return listOfCars
    }
}