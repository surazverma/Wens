package com.example.wens.operation.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wens.BuildConfig
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.repository.IWensDataSource
import com.example.wens.retrofit.WensAPIClient
import com.example.wens.status.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object WensRemoteOperation : IWensDataSource {
    val apiKey = BuildConfig.API_KEY

    override suspend fun getTopHeadlinesFromCountry(country: String): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getTopHeadlinesFromCountry(country, apiKey)
        return setResourceAccordingToResponse(response)
    }

    override suspend fun getTopHeadlinesFromSources(sources: String): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getTopHeadLinesFromSources(sources, apiKey)
        return setResourceAccordingToResponse(response)
    }



    override suspend fun getTopHeadlinesFromCategory(category: String): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getTopHeadlinesFromCategory(category, apiKey)
        return setResourceAccordingToResponse(response)
    }


    override suspend fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getTopHeadlinesFromCountryInCategory(category,country, apiKey)
        return setResourceAccordingToResponse(response)
    }

    override suspend fun getTopHeadlinesFromQuery(query: String): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getTopHeadlinesFromQuery(query, apiKey)
        return setResourceAccordingToResponse(response)
    }

    override suspend fun getEverythingFromQuery(query: String): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getEverythingByQuery(query, apiKey)
        return setResourceAccordingToResponse(response)
    }

    override suspend fun getEverythingFromQueryInTitle(queryInTitle: String): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getEverythingByQueryInTitle(queryInTitle, apiKey)
        return setResourceAccordingToResponse(response)
    }

    override suspend fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getEverythingByQueryAndDate(q,from,to, sortBy, apiKey)
        return setResourceAccordingToResponse(response)
    }

    private fun setResourceAccordingToResponse(response: Response<BaseListResponse<Articles>>) =
        if (response.isSuccessful) {
            Resource.Success(response.body()!!)
        } else {
            Resource.Error(response.message())
        }
}