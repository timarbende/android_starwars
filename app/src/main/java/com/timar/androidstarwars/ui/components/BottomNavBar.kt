package com.timar.androidstarwars.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timar.androidstarwars.ui.theme.AndroidStarWarsTheme
import com.timar.androidstarwars.ui.util.CharactersDestination
import com.timar.androidstarwars.ui.util.NavDestination
import com.timar.androidstarwars.ui.util.navigationDestiations

@Composable
fun BottomNavigationBar(
    onTabSelected: (NavDestination) -> Unit,
    currentDestination: NavDestination,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier){
        navigationDestiations.forEach {  destination ->
            NavigationBarItem(
                icon = { Image(
                    painterResource(destination.icon),
                    contentDescription = destination.iconDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(28.dp)
                ) },
                label = {
                    Text(destination.contentType.toString()) },
                selected = destination == currentDestination,
                onClick = {
                    onTabSelected(destination)
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    AndroidStarWarsTheme {
        BottomNavigationBar(
            currentDestination = CharactersDestination,
            onTabSelected = {}
        )
    }
}