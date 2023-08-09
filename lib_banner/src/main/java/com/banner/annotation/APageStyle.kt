package com.banner.annotation

import androidx.annotation.IntDef
import com.banner.mode.PageStyle.MULTI_PAGE
import com.banner.mode.PageStyle.MULTI_PAGE_OVERLAP
import com.banner.mode.PageStyle.MULTI_PAGE_SCALE
import com.banner.mode.PageStyle.NORMAL
import java.lang.annotation.ElementType

/**
 * 指示器页面样式
 */
@Target(AnnotationTarget.VALUE_PARAMETER)
@IntDef(NORMAL, MULTI_PAGE, MULTI_PAGE_OVERLAP, MULTI_PAGE_SCALE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Target(ElementType.PARAMETER)
annotation class APageStyle()
