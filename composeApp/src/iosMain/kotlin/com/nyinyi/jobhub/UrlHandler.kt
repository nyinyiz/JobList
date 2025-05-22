package com.nyinyi.jobhub

import com.nyinyi.jobhub.utils.PlatformUrlHandler
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

@OptIn(ExperimentalForeignApi::class)
class IOSUrlHandler : PlatformUrlHandler {
    @Suppress("UNCHECKED_CAST")
    override fun openUrl(url: String) {
        try {
            val nsUrl = NSURL.URLWithString(url) ?: return

            if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
                // Use the non-deprecated method with empty options dictionary and explicit types
                UIApplication.sharedApplication.openURL(
                    nsUrl,
                    emptyMap<String, Any>() as Map<Any?, Any?>,
                    null
                )
            }
        } catch (e: Exception) {
            println("Error opening URL: ${e.message}")
        }
    }
}