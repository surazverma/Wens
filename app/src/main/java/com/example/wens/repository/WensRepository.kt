package com.example.wens.repository


import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.util.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WensRepository @Inject constructor(private val wensRemoteSource: IWensDataSource) {


    suspend fun getTopHeadlinesFromCountry(country: String): Flow<ResultWrapper<BaseListResponse<Articles>>> {
        return wensRemoteSource.getTopHeadlinesFromCountry(country)
    }

    suspend fun getTopHeadlinesFromSources(sources: String): ResultWrapper<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromSources(sources)
    }

    suspend fun getTopHeadlinesFromCategory(category: String): ResultWrapper<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromCategory(category)
    }

    suspend fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): ResultWrapper<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromCategoryInCountry(category, country)
    }

    suspend fun getTopHeadlinesFromQuery(query: String): ResultWrapper<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromQuery(query)
    }

    suspend fun getEverythingFromQuery(query: String): ResultWrapper<BaseListResponse<Articles>> {
        return wensRemoteSource.getEverythingFromQuery(query)
    }

    suspend fun getEverythingFromQueryInTitle(qInTitle: String): ResultWrapper<BaseListResponse<Articles>> {
        return wensRemoteSource.getEverythingFromQueryInTitle(qInTitle)
    }

    suspend fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): ResultWrapper<BaseListResponse<Articles>> {
        return wensRemoteSource.getEverythingFromQueryAndDate(q, from, to, sortBy)
    }
}