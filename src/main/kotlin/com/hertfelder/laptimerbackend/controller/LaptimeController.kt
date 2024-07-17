package com.hertfelder.laptimerbackend.controller

import com.hertfelder.laptimerbackend.dto.CarDto
import com.hertfelder.laptimerbackend.dto.CircuitDto
import com.hertfelder.laptimerbackend.dto.DriverDto
import com.hertfelder.laptimerbackend.dto.LaptimeDto
import com.hertfelder.laptimerbackend.model.Car
import com.hertfelder.laptimerbackend.model.Circuit
import com.hertfelder.laptimerbackend.model.Driver
import com.hertfelder.laptimerbackend.model.Laptime
import com.hertfelder.laptimerbackend.repository.LaptimeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/laptimes")
class LaptimeController(
    @Autowired private val laptimeRepository: LaptimeRepository
) {

    @GetMapping
    fun getAllLaptimes(): List<LaptimeDto> = laptimeRepository.findAll().map {
        LaptimeDto(
            it.id,
            it.time,
            DriverDto(
                it.driver.id,
                it.driver.name
            ),
            CircuitDto(
                it.circuit.id,
                it.circuit.name,
                it.circuit.additionalInfo
            ),
            CarDto(
                it.car.id,
                it.car.name,
                it.car.additionalInfo
            )
        )
    }

    @GetMapping("/{id}")
    fun getLaptimeById(@PathVariable id: Long): LaptimeDto {
        val laptime = laptimeRepository.findById(id)
        return LaptimeDto(
            laptime.get().id,
            laptime.get().time,
            DriverDto(
                laptime.get().driver.id,
                laptime.get().driver.name
            ),
            CircuitDto(
                laptime.get().circuit.id,
                laptime.get().circuit.name,
                laptime.get().circuit.additionalInfo
            ),
            CarDto(
                laptime.get().car.id,
                laptime.get().car.name,
                laptime.get().car.additionalInfo
            )
        )
    }

    @PostMapping("/{id}")
    fun createLaptime(@RequestBody laptimeDto: LaptimeDto): LaptimeDto {
        val laptime = laptimeRepository.save(
            Laptime(
                laptimeDto.id!!,
                laptimeDto.time.toString(),
                Driver(
                    laptimeDto.driverDto.id!!,
                    laptimeDto.driverDto.name.toString()
                ),
                Circuit(
                    laptimeDto.circuitDto.id!!,
                    laptimeDto.circuitDto.name.toString(),
                    laptimeDto.circuitDto.additionalInfo.toString(),
                ),
                Car(
                    laptimeDto.carDto.id!!,
                    laptimeDto.carDto.name.toString(),
                    laptimeDto.carDto.additionalInfo.toString()
                )
            )
        )
        return LaptimeDto(
            laptime.id,
            laptime.time,
            DriverDto(
                laptime.driver.id,
                laptime.driver.name,
            ),
            CircuitDto(
                laptime.circuit.id,
                laptime.circuit.name,
                laptime.circuit.additionalInfo,
            ),
            CarDto(
                laptime.car.id,
                laptime.car.name,
                laptime.car.additionalInfo,
            )
        )
    }

    @DeleteMapping("/{id}")
    fun deleteLaptime(@PathVariable id: Long){
        val laptime = laptimeRepository.findById(id)
        laptimeRepository.delete(laptime.get())
    }
}
