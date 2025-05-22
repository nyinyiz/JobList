package com.nyinyi.jobhub.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

object TimeUtils {
    fun getRelativeTime(timestamp: Long): String {
        val createdTime = Instant.fromEpochSeconds(timestamp)
        val now = Clock.System.now()

        val duration = now - createdTime
        val daysBetween = duration.inWholeDays

        return when {
            daysBetween > 1 -> "${daysBetween} days ago"
            daysBetween == 1L -> "1 day ago"
            duration.inWholeHours >= 1 -> "${duration.inWholeHours} hours ago"
            duration.inWholeMinutes >= 1 -> "${duration.inWholeMinutes} minutes ago"
            else -> "Just now"
        }
    }
}