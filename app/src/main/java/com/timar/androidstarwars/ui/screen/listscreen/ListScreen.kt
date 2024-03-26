package com.timar.androidstarwars.ui.screen.listscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    val pagingItems = viewModel.pagingFlow.collectAsLazyPagingItems()

    val context = LocalContext.current
    LaunchedEffect(key1 = pagingItems.loadState) {
        if (pagingItems.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: ${(pagingItems.loadState.refresh as LoadState.Error).error.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    if (pagingItems.loadState.refresh is LoadState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Column {
            Text(
                text = contentType.getReadable(),
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
                    .background(
                        color = when (contentType) {
                            ContentType.Characters -> MaterialTheme.colorScheme.primaryContainer
                            ContentType.StarShips -> MaterialTheme.colorScheme.secondaryContainer
                            ContentType.Planets -> MaterialTheme.colorScheme.tertiaryContainer
                        },
                        shape = MaterialTheme.shapes.large,
                    )
                    .padding(
                        start = 4.dp,
                        end = 12.dp
                    )
            )

            Spacer(modifier = Modifier.height(24.dp))

            StarWarsList(
                data = pagingItems,
                hasFavouriteButton = contentType == ContentType.Characters,
                onListItemClick = onListItemClick,
                onFavouriteButtonClick = {
                    viewModel.onFavouriteButtonClick(it)
                }
            )
        }
    }
}