package com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

@Composable
fun ScheduleSelector(
    modifier: Modifier = Modifier,
    contentPaddingValues: PaddingValues,
    verticalArrangement: Arrangement.HorizontalOrVertical,
    cardTitles: List<String>,
    cardDetails: List<String>,
    cardOnClick: List<() -> Unit>
) {
    LazyColumn(
        contentPadding = contentPaddingValues,
        verticalArrangement = verticalArrangement
    ) {
        items(cardTitles.size) {
            ScheduleCard(
                modifier = modifier,
                cardTitles[it],
                cardDetails[it],
                cardOnClick[it]
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleCard(
    modifier: Modifier = Modifier,
    cardTitles: String,
    cardDetails: String,
    cardOnClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = cardOnClick,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = modifier
                .padding(
                    horizontal = Padding.largePadding,
                    vertical = Padding.extraExtraLargePadding
                ),
            verticalArrangement = Arrangement.spacedBy(Padding.smallPadding)
        ) {
            Text(
                text = cardTitles,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = cardDetails,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}