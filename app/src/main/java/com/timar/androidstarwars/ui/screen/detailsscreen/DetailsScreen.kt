package com.timar.androidstarwars.ui.screen.detailsscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.timar.androidstarwars.ui.util.ContentType

@Composable
fun DetailsScreen(
    id: String,
    contentType: String
) {
    val contentType = ContentType.fromString(contentType)

    Column {
        Text(
            "Mock Details Screen for $id",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            "Content Type is $contentType",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is available.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
    DetailsScreen(
        "preview",
        ContentType.Characters.toString()
    )
}