package com.sevenpeakssoftware.hassanmashraful.network.model

import io.realm.RealmList
import io.realm.RealmObject


open class CarListDto(
    var status: String? = null,
    var content: RealmList<CarDetailsDto>? = null,
    var serverTime: String? = null
) : RealmObject()