package by.koajla.episodes.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.koajla.episodes.R
import by.koajla.ui.AppTheme

@Composable
fun EpisodeBox(
    number: Int,
    name: String,
    ariDate: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.episode),
                style = MaterialTheme.typography.titleSmall
                    .copy(
                        color = MaterialTheme.colorScheme.primary
                    )
            )
            Text(
                text = number.toString(),
                style = MaterialTheme.typography.titleLarge
                    .copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
            )
        }
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
                    .copy(
                        color = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.End
                    )
            )
            Text(
                text = ariDate,
                style = MaterialTheme.typography.titleSmall
                    .copy(
                        color = MaterialTheme.colorScheme.secondary,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.End
                    )
            )
        }
    }
}

@Preview
@Composable
private fun EpisodeBoxPreview() {
    AppTheme {
        EpisodeBox(number = 1, name = "EpisodeName", ariDate = "Air Date")
    }
}