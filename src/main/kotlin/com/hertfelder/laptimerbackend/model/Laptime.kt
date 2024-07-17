package com.hertfelder.laptimerbackend.model

import com.hertfelder.laptimerbackend.dto.DriverDto
import jakarta.persistence.*

@Entity
data class Laptime(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val time: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    val driver: Driver,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_id")
    val circuit: Circuit,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    val car: Car
)
