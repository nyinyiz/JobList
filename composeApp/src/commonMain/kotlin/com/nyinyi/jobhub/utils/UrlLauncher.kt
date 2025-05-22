package com.nyinyi.jobhub.utils

expect class UrlLauncher() {
    fun openUrl(url: String)

    companion object {
        fun create(): UrlLauncher
    }
}