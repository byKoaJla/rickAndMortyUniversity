package by.koajla.episodes.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.koajla.episodes.R
import by.koajla.ui.AppTheme

@Composable
fun TotalEpisodesOnSeason(
    season: Int,
    episodes: Int
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = buildString {
            append(stringResource(id = R.string.season))
            append(" ")
            append(season)
        },
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.primary
            ))
        Text(text = buildString {
            append(episodes)
            append(" ")
            append(stringResource(id = R.string.episodes))
        },
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onSurface
                    .copy(0.8f)
            ))
    }
}

@Preview
@Composable
private fun TotalEpisodesOnSeasonPreview() {
    AppTheme {
        TotalEpisodesOnSeason(season = 1, episodes = 1)
    }
}