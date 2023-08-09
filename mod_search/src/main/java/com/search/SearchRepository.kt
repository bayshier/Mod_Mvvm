package com.search

import com.common.model.ArticleList
import com.common.model.HotSearch
import com.network.manager.ApiManager
import com.network.repository.BaseRepository

/**
 * @author Easin
 * @date   2022/3/30 18:58
 * @desc   搜索请求仓库
 */
class SearchRepository : BaseRepository() {
    /**
     * 搜索热词
     */
    suspend fun getHotSearchData(): MutableList<HotSearch>? {
        return requestResponse {
            ApiManager.api.getHotSearchData()
        }
    }


    /**
     * 搜索结果
     * @param page   页码
     * @param keyWord  关键词，支持多个，空格分开
     */
    suspend fun searchResult(page: Int, keyWord: String): ArticleList? {
        return requestResponse {
            ApiManager.api.searchResult(page, keyWord)
        }
    }

    /**
     * 收藏 or 取消收藏站内文章
     * @param id  文章id
     * @param isCollect 是否收藏
     */
    suspend fun collectArticle(id: Int, isCollect: Boolean): Any? {
        return requestResponse {
            if (!isCollect) {
                //收藏站内文章
                ApiManager.api.collectArticle(id)
            } else {
                //取消收藏站内文章
                ApiManager.api.cancelCollectArticle(id)
            }
        }
    }

}