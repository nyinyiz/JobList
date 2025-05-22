package com.nyinyi.jobhub.utils

import kotlinx.datetime.*
import kotlin.time.Duration.Companion.days

object TimeUtils {
    fun getRelativeTime(timestamp: Long): String {
        val createdTime = Instant.fromEpochSeconds(timestamp)
        val now = Clock.System.now()

        val daysBetween = (now - createdTime).inWholeDays

        return when (daysBetween) {
            0L -> "Today"
            1L -> "1 day ago"
            else -> "$daysBetween days ago"
        }
    }
}