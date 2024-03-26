package com.timar.androidstarwars.ui.components.detailsscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timar.androidstarwars.domain.model.Detail

@Composable
fun DetailChip(
    detail: Detail,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.outline
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.End
        ){
            Text(
                detail.description,
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                detail.value,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.End
            )
        }
    }
}

@Preview
@Composable
private fun DetailChipPreview() {
    val detail = Detail(
        description = "Year of Birth",
        value = "1997"
    )
    DetailChip(detail = detail)
}