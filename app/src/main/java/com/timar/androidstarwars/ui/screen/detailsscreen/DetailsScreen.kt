package com.timar.androidstarwars.ui.screen.detailsscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.timar.androidstarwars.ui.components.detailsscreen.DetailsGrid

@Composable
fun DetailsScreen (
    viewModel: DetailsScreenViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
    ) {
        Text(
            state.data.name,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.weight(1f)
        )

        DetailsGrid(
            state.data.details,
            modifier = Modifier
                .weight(2f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
    DetailsScreen()
}