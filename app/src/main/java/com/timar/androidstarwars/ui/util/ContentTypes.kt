package com.timar.androidstarwars.ui.util

enum class ContentType {
    Characters,
    StarShips,
    Planets;

    fun getReadable(): String = when(this){
        Characters -> "Characters"
        StarShips -> "Star Ships"
        Planets -> "Planets"
    }

    companion object {
        fun fromString(value: String): ContentType = when (value) {
            "Star Ships" -> StarShips
            "Planets" -> Planets
            else -> Characters
        }
    }
}