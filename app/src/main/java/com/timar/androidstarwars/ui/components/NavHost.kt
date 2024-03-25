package com.timar.androidstarwars.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.timar.androidstarwars.ui.screen.listscreen.ListScreen
import com.timar.androidstarwars.ui.util.CharactersDestination
import com.timar.androidstarwars.ui.util.ContentType
import com.timar.androidstarwars.ui.util.navigationDestiations

@Composable
fun StarWarsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = CharactersDestination.route,
        modifier = modifier
    ) {
        navigationDestiations.forEach{ destination->
            composable(route = destination.route) {
                ListScreen(contentType = destination.contentType)
            }
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route){
        launchSingleTop = true
    }
}