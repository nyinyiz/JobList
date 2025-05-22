package com.nyinyi.jobhub.utils

import com.nyinyi.jobhub.IOSUrlHandler

actual class UrlLauncher actual constructor() {
    private val urlHandler = IOSUrlHandler()

    actual fun openUrl(url: String) {
        urlHandler.openUrl(url)
    }

    actual companion object {
        actual fun create(): UrlLauncher = UrlLauncher()
    }
}