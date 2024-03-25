package com.timar.androidstarwars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.timar.androidstarwars.ui.components.BottomNavigationBar
import com.timar.androidstarwars.ui.components.StarWarsNavHost
import com.timar.androidstarwars.ui.components.navigateSingleTopTo
import com.timar.androidstarwars.ui.screen.listscreen.ListScreen
import com.timar.androidstarwars.ui.theme.AndroidStarWarsTheme
import com.timar.androidstarwars.ui.util.CharactersDestination
import com.timar.androidstarwars.ui.util.ContentType
import com.timar.androidstarwars.ui.util.navigationDestiations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStarWarsTheme {

                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentDestination = navigationDestiations.find { destination ->
                    destination.route == currentBackStack?.destination?.route
                } ?: CharactersDestination

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            currentDestination = currentDestination,
                            onTabSelected = {destination ->
                                navController.navigateSingleTopTo(destination.route)
                            }
                        )
                    },
                    content = { padding->
                        StarWarsNavHost(
                            navController = navController,
                            modifier = Modifier.padding(padding)
                        )
                    }
                )
            }
        }
    }
}