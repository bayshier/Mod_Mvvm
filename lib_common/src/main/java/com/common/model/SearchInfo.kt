package com.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author Easin
 * @date   2022/3/29 22:48
 * @desc   搜索相关
 */
@Parcelize
data class KeyWord(
    val id: String?,
    val keyWord: String
) : Parcelable

/**
 * 热门搜索
 */
@Parcelize
data class HotSearch(
    val id: Int?,
    val link: String?,
    val name: String? = "",
    val order: Int?,
    val visible: Int?
) : Parcelable