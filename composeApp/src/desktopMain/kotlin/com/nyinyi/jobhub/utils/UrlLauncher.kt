package com.nyinyi.jobhub.utils

import com.nyinyi.jobhub.DesktopUrlHandler

actual class UrlLauncher actual constructor() {
    private val urlHandler = DesktopUrlHandler()

    actual fun openUrl(url: String) {
        urlHandler.openUrl(url)
    }

    actual companion object {
        actual fun create(): UrlLauncher = UrlLauncher()
    }
}