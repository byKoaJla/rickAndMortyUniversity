package by.koajla.episodes.presentation.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.koajla.episodes.R
import by.koajla.ui.AppTheme

@Composable
fun SeasonBox(
    text: Int
) {
    Text(
        text = buildString {
            append(stringResource(id = R.string.season))
            append(" ")
            append(text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(5.dp),
        style = MaterialTheme.typography.displaySmall
            .copy(
                color = MaterialTheme.colorScheme.secondary
            ),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
private fun SeasonBoxPreview() {
    AppTheme {
        SeasonBox(text = 1)
    }
}