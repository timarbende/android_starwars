package com.timar.androidstarwars.ui.screen.listscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.ui.components.listscreen.StarWarsList
import com.timar.androidstarwars.ui.util.ContentType

@Composable
fun ListScreen(
    contentType: ContentType,
    viewModel: ListScreenViewModel = hiltViewModel<ListScreenViewModel, ListScreenViewModel.ListScreenViewModelFactory> { factory ->
        factory.create(contentType)
    },
    onListItemClick: (BaseModel) -> Unit,
) {
    val characters = viewModel.pagingFlow.collectAsLazyPagingItems()

    val context = LocalContext.current
    LaunchedEffect(key1 = characters.loadState) {
        if (characters.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: ${(characters.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    if (characters.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
    Column(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        Text(
            text = contentType.getReadable(),
            style = MaterialTheme.typography.displayMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        StarWarsList(
            data = characters,
            onListItemClick = onListItemClick
        )
    }
}