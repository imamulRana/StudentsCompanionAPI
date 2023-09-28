package com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

@Composable
fun HeadingText(modifier: Modifier = Modifier, headingText: String) {
    Text(
        modifier = modifier
            .padding(start = Padding.extraLargePadding),
        text = headingText.uppercase(),
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.bodySmall
    )
}