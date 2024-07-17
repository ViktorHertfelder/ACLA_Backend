package com.hertfelder.laptimerbackend.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Car(
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    var additionalInfo: String
)
