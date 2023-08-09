package com.banner.indicator

import androidx.viewpager.widget.ViewPager
import com.banner.options.IndicatorOptions

/**
 * IIndicator
 */
interface IIndicator : ViewPager.OnPageChangeListener {

    fun notifyDataChanged()

    fun setIndicatorOptions(options: IndicatorOptions)
}
