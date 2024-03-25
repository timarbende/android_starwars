package com.timar.androidstarwars.data.local.character

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.timar.androidstarwars.domain.model.Detail

@Entity
data class CharacterEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val numberOfFilmReferences: Int,
)
