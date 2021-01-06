package com.example.wens.repository


import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.status.Resource

class WensRepository(private val wensRemoteSource: IWensDataSource) {


    suspend fun getTopHeadlinesFromCountry(country: String): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromCountry(country)
    }

    suspend fun getTopHeadlinesFromSources(sources: String): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromSources(sources)
    }

    suspend fun getTopHeadlinesFromCategory(category: String): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromCategory(category)
    }

    suspend fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromCategoryInCountry(category, country)
    }

    suspend fun getTopHeadlinesFromQuery(query: String): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getTopHeadlinesFromQuery(query)
    }

    suspend fun getEverythingFromQuery(query: String): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getEverythingFromQuery(query)
    }

    suspend fun getEverythingFromQueryInTitle(qInTitle: String): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getEverythingFromQueryInTitle(qInTitle)
    }

    suspend fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): Resource<BaseListResponse<Articles>> {
        return wensRemoteSource.getEverythingFromQueryAndDate(q, from, to, sortBy)
    }
}