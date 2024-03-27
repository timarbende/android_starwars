package com.timar.androidstarwars.domain.model

data class BaseModel(
    val id: String,
    val name: String,
    val numberOfFilmReferences: Int,
    var isFavourite: Boolean = false,

    val details: List<Detail> = emptyList()
)
