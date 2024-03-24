package com.timar.androidstarwars.ui.util

interface NavDestination {
    val title: String
    val route: String
}

object CharactersDestination: NavDestination {
    override val title: String = "Characters"
    override val route: String
        get() = TODO("Not yet implemented")
}