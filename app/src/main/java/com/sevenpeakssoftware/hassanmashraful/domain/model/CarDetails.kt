package com.sevenpeakssoftware.hassanmashraful.domain.model

data class CarDetails(
    var id: Int,
    var title: String,
    var dateTime: String? = null,
    var content: List<CarDescription>?,
    var ingress: String? = null,
    var image: String
)