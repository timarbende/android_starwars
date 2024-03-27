package com.timar.androidstarwars.ui.components.listscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timar.androidstarwars.R
import com.timar.androidstarwars.ui.theme.AndroidStarWarsTheme

@Composable
fun StarWarsListItem(
    title: String,
    subtitle: String,
    hasFavouriteButton: Boolean,
    isFavourite: Boolean,
    onFavouriteButtonClick: () -> Unit,
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
        trailingContent = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                if(hasFavouriteButton) {
                    IconButton(onClick = onFavouriteButtonClick) {
                        Icon(
                            imageVector = if (isFavourite) Icons.Rounded.Favorite
                            else Icons.Rounded.FavoriteBorder,
                            contentDescription = "Favorite button"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Navigation icon"
                )
            }
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
            ),
            hasFavouriteButton = true,
            isFavourite = true,
            onFavouriteButtonClick = {}
        )
    }
}