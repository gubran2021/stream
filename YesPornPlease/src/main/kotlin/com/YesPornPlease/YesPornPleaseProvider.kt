package com.megix

import android.content.Context
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class YesPornPleaseProvider : Plugin() {
    override fun load(context: Context) {
        registerMainAPI(YesPornPlease())
    }
}
