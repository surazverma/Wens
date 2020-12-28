package com.example.wens.repository

import androidx.lifecycle.LiveData
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.operation.remote.WensNetworkOperation
import com.example.wens.status.Resource

class WensRepository:IWensRepository {
    val mWensNetworkOperation = WensNetworkOperation

    override fun getTopHeadlinesFromCountry(country: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getTopHeadlinesFromCountry(country)
    }

    override fun getTopHeadlinesFromSources(sources: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getTopHeadlinesFromSources(sources)
    }

    override fun getTopHeadlinesFromCategory(category: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getTopHeadlinesFromCategory(category)
    }

    override fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getTopHeadlinesFromCategoryInCountry(category, country)
    }

    override fun getTopHeadlinesFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getTopHeadlinesFromQuery(query)
    }

    override fun getEverythingFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getEverythingFromQuery(query)
    }

    override fun getEverythingFromQueryInTitle(qInTitle: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getEverythingFromQueryInTitle(qInTitle)
    }

    override fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getEverythingFromByQueryAndDate(q, from, to, sortBy)
    }
}