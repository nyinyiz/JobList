package com.nyinyi.jobhub

import com.nyinyi.jobhub.utils.PlatformUrlHandler
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class IOSUrlHandler : PlatformUrlHandler {
    override fun openUrl(url: String) {
        val nsUrl = NSURL.URLWithString(url) ?: return
        UIApplication.sharedApplication.openURL(nsUrl)
    }
}