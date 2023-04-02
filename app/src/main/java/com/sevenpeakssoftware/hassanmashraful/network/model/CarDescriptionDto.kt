package com.sevenpeakssoftware.hassanmashraful.network.model

import io.realm.RealmObject

open class CarDescriptionDto(
    var type: String? = null,
    var subject: String? = null,
    var description: String? = null
) : RealmObject()