package com.hertfelder.laptimerbackend.controller

import com.hertfelder.laptimerbackend.dto.CarDto
import com.hertfelder.laptimerbackend.dto.CircuitDto
import com.hertfelder.laptimerbackend.dto.DriverDto
import com.hertfelder.laptimerbackend.dto.LaptimeDto
import com.hertfelder.laptimerbackend.model.Circuit
import com.hertfelder.laptimerbackend.repository.CircuitRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/circuits")
class CircuitController(
    @Autowired
    private val circuitRepository: CircuitRepository
) {
    @GetMapping
    fun getAllCircuits(): List<CircuitDto> = circuitRepository.findAll()
        .map {
            CircuitDto(
                it.id,
                name = it.name,
                additionalInfo = it.additionalInfo,
            )
        }

    @GetMapping("/{id}")
    fun getCircuitById(@PathVariable id: Long): CircuitDto {
        val circuit = circuitRepository.findById(id)

        return CircuitDto(
            circuit.get().id,
            circuit.get().name,
            circuit.get().additionalInfo,
        )


    }

    @PostMapping
    fun createCircuit(@RequestBody circuitDto: CircuitDto): CircuitDto {
        val circuit = circuitRepository.save(
            Circuit(
                circuitDto.id!!,
                circuitDto.name.toString(),
                circuitDto.additionalInfo.toString(),
            )
        )
        return CircuitDto(
            circuit.id,
            circuit.name,
            circuit.additionalInfo,
        )
    }

    @PutMapping("/{id}")
    fun updateCircuitById(@PathVariable id: Long, @RequestBody circuitDto: CircuitDto): CircuitDto {
        val circuitToUpdate = circuitRepository.findById(id)
        circuitToUpdate.get().name = circuitDto.name.toString()
        circuitToUpdate.get().additionalInfo = circuitDto.additionalInfo.toString()

        val updatedCircuit = circuitRepository.save(circuitToUpdate.get())
        return CircuitDto(
            updatedCircuit.id,
            updatedCircuit.name,
            updatedCircuit.additionalInfo,
        )
    }

    @DeleteMapping("/{id}")
    fun deleteCircuit(@PathVariable id: Long) {
        val circuit = circuitRepository.findById(id)
        circuitRepository.delete(circuit.get());
    }
}