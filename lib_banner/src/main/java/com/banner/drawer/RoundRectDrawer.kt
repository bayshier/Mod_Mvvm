package com.banner.drawer

import android.graphics.Canvas
import com.banner.options.IndicatorOptions

/**
 * 圆角Drawer
 */
class RoundRectDrawer internal constructor(indicatorOptions: IndicatorOptions) : RectDrawer(
    indicatorOptions
) {

    override fun drawRoundRect(
        canvas: Canvas,
        rx: Float,
        ry: Float
    ) {
        canvas.drawRoundRect(mRectF, rx, ry, mPaint)
    }
}
