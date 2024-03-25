package com.timar.androidstarwars.ui.components.listscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.timar.androidstarwars.R
import com.timar.androidstarwars.domain.model.BaseModel

@Composable
fun StarWarsList(
    data: List<BaseModel>,
    onListItemClick: (BaseModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(data) { baseModel ->
            StarWarsListItem(
                title = baseModel.name,
                subtitle = pluralStringResource(
                    id = R.plurals.number_of_film_references,
                    baseModel.numberOfFilmReferences,
                    baseModel.numberOfFilmReferences
                ),
                modifier = Modifier.clickable {
                    onListItemClick(baseModel)
                }
            )
        }
    }
}

@Preview(device = "spec:width=1080px,height=1080px")
@Composable
private fun StarWarsListPreview() {
    val data = List(10) {
        BaseModel(
            id = "preview",
            name = it.toString(),
            numberOfFilmReferences = it
        )
    }
    StarWarsList(
        data = data,
        onListItemClick = {}
    )
}