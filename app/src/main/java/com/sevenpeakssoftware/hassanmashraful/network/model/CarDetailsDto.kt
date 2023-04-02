package com.sevenpeakssoftware.hassanmashraful.network.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class CarDetailsDto(
    @PrimaryKey
    var id: Int? = null,
    var title: String? = null,
    var dateTime: String? = null,
    var content: RealmList<CarDescriptionDto>? = null,
    var ingress: String? = null,
    var image: String? = null
) : RealmObject()