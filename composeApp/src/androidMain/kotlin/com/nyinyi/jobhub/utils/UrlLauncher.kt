package com.nyinyi.jobhub.utils

import com.nyinyi.jobhub.MyApplication
import com.nyinyi.jobhub.UrlHandler

actual class UrlLauncher actual constructor() {
    private val urlHandler: PlatformUrlHandler

    // Get the application context
    init {
        val context = MyApplication.instance
        urlHandler = UrlHandler(context)
    }

    actual fun openUrl(url: String) {
        urlHandler.openUrl(url)
    }

    actual companion object {
        actual fun create(): UrlLauncher = UrlLauncher()
    }
}