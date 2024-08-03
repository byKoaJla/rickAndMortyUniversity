package by.koajla.character.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.koajla.character.R
import by.koajla.character.domain.model.PointData
import by.koajla.ui.AppTheme

@Composable
fun Point(
    point: PointData
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = buildString {
                append(stringResource(id = point.label))
                append(":")
            },
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary
            )
        )
        point.value?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.7f
                    )
                )
            )
        }
    }
}

@Preview
@Composable
private fun PointPreview() {
    AppTheme {
        Point(point = PointData(R.string.origin, "Origin"))
    }
}