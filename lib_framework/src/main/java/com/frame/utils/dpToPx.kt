@file:JvmName("DisplayUtil")
@file:JvmMultifileClass

package com.frame.utils

import android.content.Context
import android.util.TypedValue
import androidx.annotation.Dimension
import com.frame.helper.AppHelper

fun dpToPx(dpValue: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpValue,
        AppHelper.getApplication().resources.displayMetrics
    )
}

fun dpToPx(dpValue: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(),
        AppHelper.getApplication().resources.displayMetrics
    ).toInt()
}

fun dpToPx(context: Context, @Dimension(unit = Dimension.DP) dp: Int): Float {
    val r = context.resources
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics)
}

/**
 * 所有字体均使用dp
 */
fun spToPx(spValue: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        spValue,
        AppHelper.getApplication().resources.displayMetrics
    )
}