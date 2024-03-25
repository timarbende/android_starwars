package com.timar.androidstarwars.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timar.androidstarwars.ui.theme.AndroidStarWarsTheme
import com.timar.androidstarwars.ui.util.navigationDestiations

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier){
        navigationDestiations.forEachIndexed { index, destination ->
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
                selected = index == 0,
                onClick = {}
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    AndroidStarWarsTheme {
        BottomNavigationBar()
    }
}