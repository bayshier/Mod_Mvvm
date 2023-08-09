package com.common.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author Easin
 * @date   2022/3/24 18:46
 * @desc   用户信息
 */
@Parcelize
data class User(
    val id: Int? = 0,
    val username: String?,
    var nickname: String?,
    val token: String?,
    var icon: String? = "",
    val email: String? = "",
    var password: String?,
    var signature: String?,
    var sex: String?,
    var birthday: String? = ""
) : Parcelable {
    fun getName(): String? {
        return if (!nickname.isNullOrEmpty()) {
            nickname
        } else {
            username
        }
    }
}