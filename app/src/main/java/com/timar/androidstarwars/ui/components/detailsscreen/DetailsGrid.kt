package com.timar.androidstarwars.ui.components.detailsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timar.androidstarwars.domain.model.Detail

@Composable
fun DetailsGrid(
    details: List<Detail>,
    modifier: Modifier = Modifier,
    rowSize: Int = 2
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(rowSize),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                horizontal = 8.dp,
                vertical = 16.dp
            )
    ) {
        items(details){detail ->
            DetailChip(
                detail = detail,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun DetailsGridPreview() {
    val details = MutableList(8){
        Detail(
            description = "description",
            value = "${it + 1000}"
        )
    }
    details.add(Detail(
        description = "lengthy descriptionnnnnnnnnnnnnnnnnn",
        value= "very  very very long value"
    ))
    DetailsGrid(details)
}