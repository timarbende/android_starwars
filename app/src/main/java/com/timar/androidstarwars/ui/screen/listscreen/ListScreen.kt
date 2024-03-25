package com.timar.androidstarwars.ui.screen.listscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.ui.components.listscreen.StarWarsList
import com.timar.androidstarwars.ui.util.ContentType

@Composable
fun ListScreen(
    contentType: ContentType,
    viewModel: ListScreenViewModel = hiltViewModel<ListScreenViewModel, ListScreenViewModel.ListScreenViewModelFactory>{factory ->
        factory.create(contentType)
    },
    onListItemClick: (BaseModel) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ){
        Text(
            text = contentType.getReadable(),
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(80.dp))

        StarWarsList(
            data = state.data,
            onListItemClick = onListItemClick
        )
    }
}