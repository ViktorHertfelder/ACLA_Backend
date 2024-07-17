package com.hertfelder.laptimerbackend.repository

import com.hertfelder.laptimerbackend.model.Laptime
import org.springframework.data.jpa.repository.JpaRepository

interface LaptimeRepository : JpaRepository<Laptime, Long>