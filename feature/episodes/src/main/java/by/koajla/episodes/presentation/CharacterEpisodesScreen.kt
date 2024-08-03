package by.koajla.episodes.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import by.koajla.episodes.domain.model.Episode
import by.koajla.episodes.presentation.componets.EpisodeBox
import by.koajla.episodes.presentation.componets.SeasonBox
import by.koajla.episodes.presentation.componets.TotalEpisodesOnSeason
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CharacterEpisodesScreen(
    uiState: UiState,
    goBack: () -> Unit
) {
    Scaffold(
        contentWindowInsets = WindowInsets.statusBars.add(
                WindowInsets(left = 10.dp, right = 10.dp)
                ),
        topBar = {
            LargeTopAppBar(
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = {
                    uiState.data?.let {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.displaySmall.copy(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            )
        }
    ) { ip ->
        LazyColumn(
            modifier = Modifier.padding(ip),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            if (uiState.data == null) {
                item {
                    CircularProgressIndicator()
                }
                return@LazyColumn
            }
            item {
                ShowAllSeasons(data = uiState.data.episodes)
            }
            item {
                SubcomposeAsyncImage(
                    model = uiState.data.image,
                    contentDescription = uiState.data.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(10.dp))
                )
            }

            uiState.data.episodes.forEach { (season, episodes) ->
                stickyHeader {
                    SeasonBox(text = season)
                }
                items(episodes) {episode ->
                    EpisodeBox(number = episode.episode.filter { it.isDigit() }.takeLast(2).toInt(), name = episode.name, ariDate = episode.airDate)
                }
            }
        }
    }
}

@Composable
private fun ShowAllSeasons(data: Map<Int, List<Episode>>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        data.forEach { (season, episodes) ->
            item {
                TotalEpisodesOnSeason(season = season, episodes = episodes.count())
            }
        }

    }
}


