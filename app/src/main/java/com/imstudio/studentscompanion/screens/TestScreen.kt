package com.imstudio.studentscompanion.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestScreen(modifier: Modifier = Modifier, effect: (Composable) -> Composable) {
    val scope = rememberCoroutineScope()
    Column {

    }
}



@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
}