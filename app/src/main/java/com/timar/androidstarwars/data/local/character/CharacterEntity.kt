package com.timar.androidstarwars.data.local.character

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val numberOfFilmReferences: Int,
    val isFavourite: Boolean
)