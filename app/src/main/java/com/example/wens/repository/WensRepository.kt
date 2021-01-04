package com.example.wens.repository

import androidx.lifecycle.LiveData
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.operation.remote.WensRemoteOperation
import com.example.wens.status.Resource

class WensRepository(private val wensRemoteSource: IWensDataSource) {


    fun getTopHeadlinesFromCountry(country: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getTopHeadlinesFromCountry(country)
    }

    fun getTopHeadlinesFromSources(sources: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getTopHeadlinesFromSources(sources)
    }

    fun getTopHeadlinesFromCategory(category: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getTopHeadlinesFromCategory(category)
    }

    fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getTopHeadlinesFromCategoryInCountry(category, country)
    }

    fun getTopHeadlinesFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getTopHeadlinesFromQuery(query)
    }

    fun getEverythingFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getEverythingFromQuery(query)
    }

    fun getEverythingFromQueryInTitle(qInTitle: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getEverythingFromQueryInTitle(qInTitle)
    }

    fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRemoteSource.getEverythingFromQueryAndDate(q, from, to, sortBy)
    }
}