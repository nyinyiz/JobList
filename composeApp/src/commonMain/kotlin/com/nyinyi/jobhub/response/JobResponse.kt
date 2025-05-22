package com.nyinyi.jobhub.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Job(
    val slug: String = "",
    @SerialName("company_name") val companyName: String = "",
    val title: String = "",
    val description: String = "",
    val remote: Boolean = false,
    val url: String = "",
    val tags: List<String> = emptyList(),
    @SerialName("job_types") val jobTypes: List<String> = emptyList(),
    val location: String = "",
    @SerialName("created_at") val createdAt: Long = 0
) {
    companion object {
        fun getSampleJobs(): List<Job> = listOf(
            Job(
                slug = "slug",
                companyName = "companyName",
                title = "title",
                description = "description",
                remote = false,
                url = "url",
                tags = emptyList(),
                jobTypes = emptyList(),
                location = "location",
                createdAt = 0
            )
        )
    }
}


@Serializable
data class ApiResponse(
    val data: List<Job> = emptyList()
)