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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.timar.androidstarwars.ui.components.BottomNavigationBar
import com.timar.androidstarwars.ui.screen.listscreen.ListScreen
import com.timar.androidstarwars.ui.theme.AndroidStarWarsTheme
import com.timar.androidstarwars.ui.util.ContentType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidStarWarsTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar()
                    },
                    content = {padding->
                        ListScreen(
                            contentType = ContentType.Planets,
                            modifier = Modifier.padding(padding)
                        )
                    }
                )
            }
        }
    }
}