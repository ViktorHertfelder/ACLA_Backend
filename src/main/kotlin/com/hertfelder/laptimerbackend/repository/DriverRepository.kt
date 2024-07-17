package com.hertfelder.laptimerbackend.repository

import com.hertfelder.laptimerbackend.model.Driver
import org.springframework.data.jpa.repository.JpaRepository

interface DriverRepository : JpaRepository<Driver, Long>