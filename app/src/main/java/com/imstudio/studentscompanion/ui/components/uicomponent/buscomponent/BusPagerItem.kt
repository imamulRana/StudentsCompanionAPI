package com.imstudio.studentscompanion.ui.components.uicomponent.buscomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.imstudio.studentscompanion.model.BusItem
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

@Composable
fun BusPagerItem(
    modifier: Modifier = Modifier,
    busItem: BusItem
) {
    val textStyle = MaterialTheme.typography.bodyMedium
    val textColor = MaterialTheme.colorScheme.onPrimary
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(Padding.smallPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier.weight(.6f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = modifier
                        .padding(Padding.smallPadding),
                    text = busItem.busTime, style = textStyle,
                    color = textColor
                )
            }
            Box(
                modifier = modifier
                    .weight(2f)
                    .padding(start = Padding.mediumPadding),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = modifier
                        .padding(Padding.smallPadding),
                    text = busItem.busRoute.uppercase(), style = textStyle,
                    color = textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box(
                modifier = modifier
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = modifier
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        modifier = modifier
                            .padding(
                                vertical = Padding.smallPadding,
                                horizontal = Padding.mediumPadding
                            ),
                        text = "bus no. ${busItem.busNumber}".uppercase(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }
}

