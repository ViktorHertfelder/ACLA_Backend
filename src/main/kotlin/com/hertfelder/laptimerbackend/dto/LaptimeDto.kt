package com.hertfelder.laptimerbackend.dto

import com.hertfelder.laptimerbackend.model.Circuit

data class LaptimeDto(
    val id: Long?,
    val time: String?,
    val driverDto: DriverDto,
    val circuitDto: CircuitDto,
    val carDto: CarDto,
)
