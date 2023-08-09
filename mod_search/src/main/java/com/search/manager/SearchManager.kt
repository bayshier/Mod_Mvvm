package com.search.manager

import com.common.constant.SEARCH_HISTORY_INFO
import com.frame.ext.toBeanOrNull
import com.frame.ext.toJson
import com.tencent.mmkv.MMKV

/**
 * @author Easin
 * @date   2022/3/30 23:47
 * @desc   搜索管理类
 */
object SearchManager {

    private var mmkv = MMKV.defaultMMKV()

    /**
     * 保存搜索历史
     * @param searchList
     */
    fun saveSearchHistory(searchList: MutableList<String>) {
        val histories = getSearchHistory() ?: mutableListOf()
        histories.addAll(searchList)
        val duplicateRemoval = histories.distinct()
        mmkv.encode(SEARCH_HISTORY_INFO, duplicateRemoval.toJson(true))
    }

    /**
     * 添加搜索历史
     * @param keyWord
     */
    fun addSearchHistory(keyWord: String) {
        val histories = getSearchHistory() ?: mutableListOf()
        histories.add(keyWord)
        val duplicateRemoval = histories.distinct()
        mmkv.encode(SEARCH_HISTORY_INFO, duplicateRemoval.toJson())
    }

    /**
     * 获取搜索历史
     * @return MutableList
     */
    fun getSearchHistory(): MutableList<String>? {
        return mmkv.decodeString(SEARCH_HISTORY_INFO)?.toBeanOrNull()
    }

    /**
     * 清除搜索历史数据
     */
    fun clearSearchHistory() {
        mmkv.encode(SEARCH_HISTORY_INFO, "")
    }
}