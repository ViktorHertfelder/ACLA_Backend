package com.hertfelder.laptimerbackend.controller

import com.hertfelder.laptimerbackend.dto.CarDto
import com.hertfelder.laptimerbackend.model.Car
import com.hertfelder.laptimerbackend.repository.CarRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/cars")
@RestController
class CarController(
    @Autowired private val carRepository: CarRepository
) {

    @GetMapping
    fun getAllCars(): List<CarDto> = carRepository.findAll().map { CarDto(it.id, it.name, it.additionalInfo) }

    @GetMapping("/{id}")
    fun getCarById(@PathVariable id: Long): CarDto {
        val car = carRepository.findById(id)
        return CarDto(
            car.get().id,
            car.get().name,
            car.get().additionalInfo
        )
    }

    @PostMapping
    fun createCar(@RequestBody carDto: CarDto): CarDto {
        val car = carRepository.save(
            Car(
                carDto.id!!,
                carDto.name.toString(),
                carDto.additionalInfo.toString()
            )
        )
        return CarDto(
            car.id,
            car.name,
            car.additionalInfo
        )
    }

    @PutMapping("/{id}")
    fun updateCarById(@PathVariable id: Long, @RequestBody carDto: CarDto): CarDto {
        val carToUpdate = carRepository.findById(id)
        carToUpdate.get().name = carDto.name.toString()
        carToUpdate.get().additionalInfo = carDto.additionalInfo.toString()

        val updatedCar = carRepository.save(carToUpdate.get())
        return CarDto(
            updatedCar.id,
            updatedCar.name,
            updatedCar.additionalInfo
        )
    }

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: Long) {
        val car = carRepository.findById(id)
        carRepository.delete(car.get())
    }
}