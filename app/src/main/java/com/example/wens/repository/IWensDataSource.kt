package com.example.wens.repository

import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.util.ResultWrapper

interface IWensDataSource {

    suspend fun getTopHeadlinesFromCountry(country: String): ResultWrapper<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromSources(sources: String): ResultWrapper<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromCategory(category: String): ResultWrapper<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): ResultWrapper<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromQuery(query: String): ResultWrapper<BaseListResponse<Articles>>

    suspend fun getEverythingFromQuery(query: String): ResultWrapper<BaseListResponse<Articles>>

    suspend fun getEverythingFromQueryInTitle(qInTitle: String): ResultWrapper<BaseListResponse<Articles>>

    suspend fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): ResultWrapper<BaseListResponse<Articles>>
}