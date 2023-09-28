package com.imstudio.studentscompanion.ui.components.uicomponent.classcomponent

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.imstudio.studentscompanion.model.Classes
import com.imstudio.studentscompanion.ui.components.modifiers.Padding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClassPager(
    modifier: Modifier = Modifier,
    state: PagerState,
    classes: List<Classes>
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = Padding.extraExtraLargePadding),
        verticalArrangement = Arrangement.spacedBy(Padding.largePadding)
    ) {
        Column(
            modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                //For Fixing The Error Of IndexOutOfBounds
                text = if (classes.size > state.currentPage) classes[state.currentPage].day
                else "No Data Available",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            HorizontalPager(
                modifier = modifier
                    .fillMaxSize(),
                pageCount = classes.size,
                contentPadding = PaddingValues(
                    horizontal = Padding.extraExtraLargePadding,
                    vertical = Padding.smallPadding
                ),
                pageSpacing = Padding.mediumPadding,
                state = state
            ) { day ->
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(25.dp))
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(Padding.hugePadding),
                        contentPadding = PaddingValues(
                            start = Padding.mediumPadding,
                            top = Padding.extraLargePadding,
                            bottom = Padding.extraLargePadding,
                            end = Padding.largePadding
                        )
                    ) {
                        items(classes[day].classItem.size) {
                            ClassPagerItem(classItem = classes[day].classItem[it])
                        }
                    }
                }
            }
        }
    }
}