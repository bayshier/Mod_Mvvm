package com.banner.annotation

import androidx.annotation.IntDef
import com.banner.mode.IndicatorSlideMode.Companion.COLOR
import com.banner.mode.IndicatorSlideMode.Companion.NORMAL
import com.banner.mode.IndicatorSlideMode.Companion.SCALE
import com.banner.mode.IndicatorSlideMode.Companion.SMOOTH
import com.banner.mode.IndicatorSlideMode.Companion.WORM

/**
 * 指示器滑动模式
 */
@IntDef(NORMAL, SMOOTH, WORM, COLOR, SCALE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class AIndicatorSlideMode
