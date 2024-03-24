package com.timar.androidstarwars.ui.components.List

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import com.timar.androidstarwars.R
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.ui.components.StarWarsListItem

@Composable
fun StarWarsList(
    state: StarWarsListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(state.data){ baseModel->
            StarWarsListItem(
                title = baseModel.name,
                subtitle = pluralStringResource(
                    id = R.plurals.number_of_film_references,
                    baseModel.numberOfFilmReferences,
                    baseModel.numberOfFilmReferences
                )
            )
        }
    }
}

@Preview(device = "spec:width=1080px,height=1080px")
@Composable
private fun StarWarsListPreview() {
    val state = StarWarsListState(
        data = List(10){
            BaseModel(
                name = it.toString(),
                numberOfFilmReferences = it
            )
        }
    )
    StarWarsList(state = state)
}