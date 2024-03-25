package com.timar.androidstarwars.ui.util

import com.timar.androidstarwars.R

interface NavDestination {
    val icon: Int
    val iconDescription: String
    val route: String
    val contentType: ContentType
}

val navigationDestiations = listOf(
    CharactersDestination,
    StarShipsDestination,
    PlanetsDestination
)

object CharactersDestination: NavDestination {
    override val icon: Int = R.drawable.icon_characters
    override val iconDescription: String = "Characters Tab Item Icon"
    override val route: String = "characters"
    override val contentType: ContentType = ContentType.Characters
}

object StarShipsDestination: NavDestination {
    override val icon: Int = R.drawable.icon_starships
    override val iconDescription: String = "Star Ships Tab Item Icon"
    override val route: String = "starships"
    override val contentType: ContentType = ContentType.StarShips
}

object PlanetsDestination: NavDestination {
    override val icon: Int = R.drawable.icon_planets
    override val iconDescription: String = "Planets Tab Item Icon"
    override val route: String = "planets"
    override val contentType: ContentType = ContentType.Planets
}