package com.imstudio.studentscompanion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.imstudio.studentscompanion.StudentsCompanionViewModel
import com.imstudio.studentscompanion.navigation.Screens
import com.imstudio.studentscompanion.ui.components.modifiers.Padding
import com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent.HeadingText
import com.imstudio.studentscompanion.ui.components.uicomponent.commoncomponent.ScheduleSelector

@Composable
fun BusRouteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(
        modifier = modifier
    ) {
        Spacer(
            modifier = modifier.height(Padding.extraLargePadding)
        )
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(Padding.largePadding)
        ) {

            HeadingText(headingText = "available routes")
            ScheduleSelector(
                modifier = modifier,
                contentPaddingValues = PaddingValues(horizontal = Padding.largePadding),
                verticalArrangement = Arrangement.spacedBy(Padding.mediumPadding),
                cardTitles = listOf("Up Routes", "Down Routes"),
                cardDetails = listOf(
                    "Bus Routes & Timing From Your Home To Campus",
                    "Bus Routes & Timing From Campus To Your Home"
                ),
                cardOnClick =
                listOf({
                    navController.navigate(Screens.BusUpSchedules.route)
                }, {
                    navController.navigate(Screens.BusDownSchedules.route)
                })
            )
        }
    }
}