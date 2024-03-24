package com.timar.androidstarwars.ui.util

enum class ContentType {
    Characters,
    StarShips,
    Planets;

    override fun toString(): String = when(this){
        Characters -> "Characters"
        StarShips -> "Star Ships"
        Planets -> "Planets"
    }
}