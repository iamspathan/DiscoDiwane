package dev.iamspathan.screenshotcompose

import android.graphics.Bitmap
import android.view.View

class Screenshot  {

    companion object {

        fun takeScreenshot(v: View): Bitmap? {
            v.isDrawingCacheEnabled = true
            v.buildDrawingCache(true)
            val b = Bitmap.createBitmap(v.drawingCache)
            v.isDrawingCacheEnabled = false
            return b
        }

        fun takeScreenshotofRootView(v: View): Bitmap? {
            return takeScreenshot(v.rootView)
        }
    }

}