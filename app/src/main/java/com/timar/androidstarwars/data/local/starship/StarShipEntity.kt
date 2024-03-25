package com.timar.androidstarwars.data.local.starship

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StarShipEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val numberOfFilmReferences: Int,
)
