package com.timar.androidstarwars.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.timar.androidstarwars.ui.screen.detailsscreen.DetailsScreen
import com.timar.androidstarwars.ui.screen.listscreen.ListScreen
import com.timar.androidstarwars.ui.screen.listscreen.ListScreenViewModel
import com.timar.androidstarwars.ui.util.CharactersDestination
import com.timar.androidstarwars.ui.util.ContentType
import com.timar.androidstarwars.ui.util.DetailsDestination
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
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        navigationDestiations.forEach{ destination->
            composable(route = destination.route) {
                ListScreen(
                    contentType = destination.contentType,
                    onListItemClick = {data->
                        navController.navigate("details/${destination.contentType}&${data.id}")
                    }
                )
            }
        }
        composable(
            route = DetailsDestination.routeWithArgs,
            arguments = DetailsDestination.arguments
        ) {
            DetailsScreen(
                onDismiss = {
                    navController.popBackStack()
                }
            )
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route){
        launchSingleTop = true
    }
}