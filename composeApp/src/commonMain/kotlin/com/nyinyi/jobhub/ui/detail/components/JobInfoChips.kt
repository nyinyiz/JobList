package com.nyinyi.jobhub.ui.detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nyinyi.jobhub.response.Job
import com.nyinyi.jobhub.utils.TimeUtils


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JobInfoChips(job: Job) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Location chip
        ElevatedSuggestionChip(
            onClick = { },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "📍",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(job.location)
                }
            },
            colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier.weight(1f),
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant
            )
        )

        if (job.remote) {
            ElevatedSuggestionChip(
                onClick = { },
                label = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "🏡",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("Remote")
                    }
                },
                colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                border = BorderStroke(
                    1.dp,
                    MaterialTheme.colorScheme.outlineVariant
                )
            )
        }

        // Posted date
        ElevatedSuggestionChip(
            onClick = { },
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "🕒",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(TimeUtils.getRelativeTime(job.createdAt))
                }
            },
            colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outlineVariant
            )
        )
    }
}
