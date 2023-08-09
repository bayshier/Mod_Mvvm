package com.frame.utils

import android.graphics.drawable.ColorDrawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.frame.helper.AppHelper

/**
 * @author Easin
 * @date   2022/3/20 7:39
 * @desc   资源获取工具类
 */
fun getStringArrayFromResource(@ArrayRes arrayId: Int): Array<String> {
    return AppHelper.getApplication().resources.getStringArray(arrayId)
}

fun getStringFromResource(@StringRes stringId: Int): String {
    return AppHelper.getApplication().getString(stringId)
}

fun getStringFromResource(@StringRes stringId: Int, vararg formatArgs: Any?): String? {
    return AppHelper.getApplication().getString(stringId, *formatArgs)
}

fun getColorFromResource(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(AppHelper.getApplication(), colorRes)
}

fun getDimensionFromResource(@DimenRes dimenRes: Int): Int {
    return AppHelper.getApplication().resources
            .getDimensionPixelSize(dimenRes)
}

fun getColorDrawable(@ColorRes colorRes: Int): ColorDrawable? {
    return ColorDrawable(
        ContextCompat.getColor(
            AppHelper.getApplication(),
            colorRes
        )
    )
}