package com.imstudio.studentscompanion.ui.components.uicomponent.classcomponent

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.imstudio.studentscompanion.model.ClassItem
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

@Composable
fun ClassPagerItem(
    modifier: Modifier = Modifier,
    classItem: ClassItem
) {
    val textStyle = MaterialTheme.typography.bodyMedium
    val textColor = MaterialTheme.colorScheme.onPrimary
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier
                    .weight(.5f), contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = modifier
                        .padding(Padding.smallPadding),
                    text = "${classItem.classStart}\n-\n${classItem.classEnd}",
                    textAlign = TextAlign.Center,
                    style = textStyle,
                    color = textColor
                )
            }
//            Spacer(modifier = modifier.width(Padding.largePadding))
            Box(
                modifier = modifier.weight(2f), contentAlignment = Alignment.CenterStart
            ) {
                Column(
                    modifier = modifier
                        // .fillMaxWidth(),
                        .padding(horizontal = Padding.largePadding),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
                ) {
                    Text(
                        text = classItem.classSubject.replaceFirstChar { c: Char -> c.uppercase() },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        textAlign = TextAlign.Start,
                        style = textStyle, color = textColor
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Padding.smallPadding),
                    ) {
                        Box(
                            modifier = modifier
                                .clip(MaterialTheme.shapes.extraLarge)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(
                                    vertical = Padding.smallPadding,
                                    horizontal = Padding.mediumPadding
                                )
                        ) {
                            Text(
                                text = classItem.classCode.uppercase(),
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Box(
                            modifier = modifier
                                .clip(MaterialTheme.shapes.extraLarge)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(
                                    vertical = Padding.smallPadding,
                                    horizontal = Padding.mediumPadding
                                )
                        ) {
                            Text(
                                text = classItem.teacherInit.uppercase(),
                                color = MaterialTheme.colorScheme.secondary,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            Box(
                modifier = modifier
                    .weight(.5f), contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = modifier
                        .padding(Padding.smallPadding),
                    text = classItem.classRoom.uppercase(),
                    textAlign = TextAlign.Center,
                    color = textColor,
                    style = textStyle
                )
            }
        }
    }
}