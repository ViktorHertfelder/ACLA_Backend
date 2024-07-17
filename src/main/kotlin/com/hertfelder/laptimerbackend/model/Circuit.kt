package com.hertfelder.laptimerbackend.model

import jakarta.persistence.*

@Entity
data class Circuit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var name: String,
    var additionalInfo: String,
){
}
