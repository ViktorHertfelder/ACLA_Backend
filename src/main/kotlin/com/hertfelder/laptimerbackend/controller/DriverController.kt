package com.hertfelder.laptimerbackend.controller

import com.hertfelder.laptimerbackend.dto.DriverDto
import com.hertfelder.laptimerbackend.model.Driver
import com.hertfelder.laptimerbackend.repository.DriverRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/drivers")
class DriverController(
    @Autowired private val driverRepository: DriverRepository
) {

    @GetMapping
    fun getAllDrivers(): List<DriverDto> = driverRepository.findAll().map { DriverDto(it.id, it.name) }

    @GetMapping("/{id}")
    fun getDriverById(@PathVariable id: Long): DriverDto {
        val driver = driverRepository.findById(id)
        return DriverDto(
            driver.get().id,
            driver.get().name
        )
    }

    @PostMapping("/{id}")
    fun createDriver(@RequestBody driverDto: DriverDto): DriverDto {
        val driver = driverRepository.save(
            Driver(
                driverDto.id!!,
                driverDto.name.toString()
            )
        )
        return DriverDto(
            driver.id,
            driver.name
        )
    }

    @PutMapping("/{id}")
    fun updateDriverById(@PathVariable id: Long, @RequestBody driverDto: DriverDto): DriverDto {
        val driverToUpdate = driverRepository.findById(id)
        driverToUpdate.get().name = driverDto.name.toString()

        val updatedDriver = driverRepository.save(driverToUpdate.get())
        return DriverDto(
            updatedDriver.id,
            updatedDriver.name
        )
    }

    @DeleteMapping("/{id}")
    fun deleteDriver(@PathVariable id: Long) {
        val driver = driverRepository.findById(id)
        driverRepository.delete(driver.get())
    }
}
