package com.hertfelder.laptimerbackend.repository

import com.hertfelder.laptimerbackend.model.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository : JpaRepository<Car, Long>