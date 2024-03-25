package com.timar.androidstarwars.ui.screen.listscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.ui.components.StarWarsList
import com.timar.androidstarwars.ui.util.ContentType

@Composable
fun ListScreen(
    contentType: ContentType,
    viewModel: ListScreenViewModel = hiltViewModel<ListScreenViewModel, ListScreenViewModel.ListScreenViewModelFactory>{factory ->
        factory.create(contentType)
    },
    onListItemClick: (BaseModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column (
        modifier = modifier
    ){
        Text(
            text = contentType.getReadable(),
            style = MaterialTheme.typography.displayMedium
        )

        StarWarsList(
            data = state.data,
            onListItemClick = onListItemClick
        )
    }
}