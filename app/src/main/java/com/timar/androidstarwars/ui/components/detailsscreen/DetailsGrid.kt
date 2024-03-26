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

    val size = details.size
    var i = 0
    Column(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.medium
            )
            .padding(horizontal = 8.dp)
        ,
    ){
        while (i < size) {
            var j = 0
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                    )
            ) {
                while (j < rowSize && i + j < size) {
                    DetailChip(
                        detail = details[i + j],
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp)
                    )
                    j++
                }
                if(j < rowSize){
                    Box(modifier = Modifier.weight(1f))
                }
            }
            i += j
        }
        Spacer(modifier = Modifier.height(16.dp))
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