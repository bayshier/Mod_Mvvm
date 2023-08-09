package com.banner.drawer

import android.graphics.Canvas

import com.banner.options.IndicatorOptions

/**
 * DashDrawer
 */
class DashDrawer internal constructor(indicatorOptions: IndicatorOptions) : RectDrawer(
    indicatorOptions
) {

    override fun drawDash(canvas: Canvas) {
        canvas.drawRect(mRectF, mPaint)
    }
}
