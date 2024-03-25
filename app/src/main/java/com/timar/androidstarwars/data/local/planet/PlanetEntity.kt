package com.timar.androidstarwars.data.local.planet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlanetEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val numberOfFilmReferences: Int,
)
