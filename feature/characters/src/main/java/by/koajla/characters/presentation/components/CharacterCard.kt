package by.koajla.characters.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.koajla.characters.R
import by.koajla.characters.domain.model.Characters
import coil.compose.SubcomposeAsyncImage

@Composable
fun CharacterCard(
    character: Characters,
    onTap: (Int) -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .clickable {
                onTap(character.id)
            }
    ) {
        SubcomposeAsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            loading = {
                CircularProgressIndicator()
            }
        )
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(character.status.color)
                )
                Text(
                    text = buildString {
                        append(character.status.displayName)
                        append(" - ")
                        append(character.species)
                    },
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
            }
            Column {
                Text(
                    text = stringResource(id = R.string.last_known_location),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Text(
                    text = character.location,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                            .copy(0.8f)
                    )
                )
            }

            Column {
                Text(
                    text = stringResource(id = R.string.first_seen_in),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
                Text(
                    text = character.episode,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface
                            .copy(0.8f)
                    )
                )
            }
        }
    }
}