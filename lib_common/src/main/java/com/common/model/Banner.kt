package com.common.model

/**
 * @author Easin
 * @date   2022/3/7 22:52
 * @desc   banner
 */
data class Banner(
    val id: Int? = 0,
    val url: String? = "", //网站地址
    val imagePath: String? = "", //图片地址
    val title: String? = "",
    val desc: String? = "",
    val isVisible: Int? = 0,
    val order: Int? = 0,
    val type: Int? = 0
)