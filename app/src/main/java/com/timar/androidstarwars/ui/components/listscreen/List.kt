package com.timar.androidstarwars.ui.components.listscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.timar.androidstarwars.R
import com.timar.androidstarwars.domain.model.BaseModel

@Composable
fun StarWarsList(
    data: LazyPagingItems<BaseModel>,
    onListItemClick: (BaseModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(count = data.itemCount) { index ->
            val item = data[index]
            if (item != null) {
                StarWarsListItem(
                    title = item.name,
                    subtitle = pluralStringResource(
                        id = R.plurals.number_of_film_references,
                        item.numberOfFilmReferences,
                        item.numberOfFilmReferences
                    ),
                    modifier = Modifier.clickable {
                        onListItemClick(item)
                    }
                )
            }
        }
        item{
            if (data.loadState.append is LoadState.Loading){
                CircularProgressIndicator()
            }
        }
    }
}

/*@Preview(device = "spec:width=1080px,height=1080px")
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
}*/