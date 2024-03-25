package com.timar.androidstarwars.ui.util

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.timar.androidstarwars.R

interface NavDestination {
    val icon: Int
    val iconDescription: String
    val route: String
    var contentType: ContentType
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
    override var contentType: ContentType = ContentType.Characters
}

object StarShipsDestination: NavDestination {
    override val icon: Int = R.drawable.icon_starships
    override val iconDescription: String = "Star Ships Tab Item Icon"
    override val route: String = "starships"
    override var contentType: ContentType = ContentType.StarShips
}

object PlanetsDestination: NavDestination {
    override val icon: Int = R.drawable.icon_planets
    override val iconDescription: String = "Planets Tab Item Icon"
    override val route: String = "planets"
    override var contentType: ContentType = ContentType.Planets
}

object DetailsDestination: NavDestination {
    override val icon: Int = R.drawable.icon_planets
    override val iconDescription: String = "this value is not used"
    override val route: String = "details"
    override var contentType: ContentType = ContentType.Planets

    const val itemIdArgument = "itemId"
    const val contentTypeArgument = "contentType"

    val routeWithArgs = "${route}/{${contentTypeArgument}}&{${itemIdArgument}}"
    val arguments = listOf(
        // At the beginning I was implementing this via NavType.EnumType(ContentType::class.java), but deserializing required min Api33,
        // so I decided to work with string representations for better compatibility
        navArgument(contentTypeArgument) {type = NavType.EnumType(ContentType::class.java) },
        navArgument(itemIdArgument) {type = NavType.StringType}
    )
}