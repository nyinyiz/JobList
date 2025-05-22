package com.nyinyi.jobhub.navigation.route

import kotlinx.serialization.Serializable

sealed interface Routes {

    @Serializable
    data class JobDetail(
        val jobId: String
    ) : Routes

    @Serializable
    data object JobList : Routes
}