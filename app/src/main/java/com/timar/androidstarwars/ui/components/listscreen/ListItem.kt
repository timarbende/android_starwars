package com.timar.androidstarwars.ui.components.listscreen

import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.timar.androidstarwars.R
import com.timar.androidstarwars.ui.theme.AndroidStarWarsTheme

@Composable
fun StarWarsListItem(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineContent = {
            Text(
                title,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        supportingContent = {
            Text(
                subtitle,
                style = MaterialTheme.typography.titleSmall
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun StarWarsListItemPreview() {
    AndroidStarWarsTheme {
        StarWarsListItem(
            title = "Luke Skywalker",
            subtitle = pluralStringResource(
                id = R.plurals.number_of_film_references,
                5,
                5
            )
        )
    }
}