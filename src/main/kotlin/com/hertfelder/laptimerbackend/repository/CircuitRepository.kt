package com.hertfelder.laptimerbackend.repository

import com.hertfelder.laptimerbackend.model.Circuit
import org.springframework.data.jpa.repository.JpaRepository

interface CircuitRepository : JpaRepository<Circuit, Long>