package com.example.wens.repository

import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.status.Resource
import javax.inject.Inject

interface IWensDataSource {

    suspend fun getTopHeadlinesFromCountry(country: String): Resource<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromSources(sources: String): Resource<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromCategory(category: String): Resource<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): Resource<BaseListResponse<Articles>>

    suspend fun getTopHeadlinesFromQuery(query: String): Resource<BaseListResponse<Articles>>

    suspend fun getEverythingFromQuery(query: String): Resource<BaseListResponse<Articles>>

    suspend fun getEverythingFromQueryInTitle(qInTitle: String): Resource<BaseListResponse<Articles>>

    suspend fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): Resource<BaseListResponse<Articles>>
}