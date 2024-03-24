package com.timar.androidstarwars.ui.util

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.timar.androidstarwars.R
import javax.inject.Inject

interface NavDestination {
    val titleId: Int
    val icon: Int
    val iconDescription: String
    val route: String
}

val navigationDestiations = listOf(
    CharactersDestination,
    StarShipsDestination,
    PlanetsDestination
)

object CharactersDestination: NavDestination {
    override val titleId: Int = R.string.characters
    override val icon: Int = R.drawable.icon_characters
    override val iconDescription: String = "Characters Tab Item Icon"
    override val route: String = ""
}

object StarShipsDestination: NavDestination {
    override val titleId: Int = R.string.star_ships
    override val icon: Int = R.drawable.icon_starships
    override val iconDescription: String = "Star Ships Tab Item Icon"
    override val route: String = ""
}

object PlanetsDestination: NavDestination {
    override val titleId: Int = R.string.planets
    override val icon: Int = R.drawable.icon_planets
    override val iconDescription: String = "Planets Tab Item Icon"
    override val route: String = ""
}