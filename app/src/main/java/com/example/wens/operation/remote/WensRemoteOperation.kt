package com.example.wens.operation.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.wens.BuildConfig
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.model.responses.ErrorResponse
import com.example.wens.pagination.WensPagingSource
import com.example.wens.repository.IWensDataSource
import com.example.wens.retrofit.WensAPIClient
import com.example.wens.util.ResultWrapper
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.flow.Flow


object WensRemoteOperation : IWensDataSource {
    private const val apiKey = BuildConfig.API_KEY

    override suspend fun getTopHeadlinesStreamFromCountry(country: String): Flow<PagingData<Articles>> = Pager(
        config = PagingConfig(pageSize = 2, maxSize = 10, enablePlaceholders = false),
        pagingSourceFactory = { WensPagingSource(WensAPIClient.getClient(), country) }
    ).flow

    override suspend fun getTopHeadlinesFromSources(sources: String): ResultWrapper<BaseListResponse<Articles>> {
        val response = WensAPIClient.getClient().getTopHeadLinesFromSources(sources, apiKey)
        return handleResponseResult(response)
    }


    override suspend fun getTopHeadlinesFromCategory(category: String): ResultWrapper<BaseListResponse<Articles>> {
        val response = WensAPIClient.getClient().getTopHeadlinesFromCategory(category, apiKey)
        return handleResponseResult(response)
    }


    override suspend fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): ResultWrapper<BaseListResponse<Articles>> {
        val response = WensAPIClient.getClient()
            .getTopHeadlinesFromCountryInCategory(category, country, apiKey)
        return handleResponseResult(response)
    }

    override suspend fun getTopHeadlinesFromQuery(query: String): ResultWrapper<BaseListResponse<Articles>> {
        val response = WensAPIClient.getClient().getTopHeadlinesFromQuery(query, apiKey)
        return handleResponseResult(response)
    }

    override suspend fun getEverythingFromQuery(query: String): ResultWrapper<BaseListResponse<Articles>> {
        val response = WensAPIClient.getClient().getEverythingByQuery(query, apiKey)
        return handleResponseResult(response)
    }

    override suspend fun getEverythingFromQueryInTitle(queryInTitle: String): ResultWrapper<BaseListResponse<Articles>> {
        val response = WensAPIClient.getClient().getEverythingByQueryInTitle(queryInTitle, apiKey)
        return handleResponseResult(response)
    }

    override suspend fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): ResultWrapper<BaseListResponse<Articles>> {
        val response =
            WensAPIClient.getClient().getEverythingByQueryAndDate(q, from, to, sortBy, apiKey)
        return handleResponseResult(response)
    }

    private fun handleResponseResult(response: NetworkResponse<BaseListResponse<Articles>, ErrorResponse>) =
        when (response) {
            is NetworkResponse.Success -> {
                ResultWrapper.Success(response.body)
            }
            is NetworkResponse.NetworkError -> {
                ResultWrapper.NetworkError(response.error.message)
            }
            is NetworkResponse.ServerError -> {
                ResultWrapper.ServerError(response.code, response.body?.message)
            }
            is NetworkResponse.UnknownError -> {
                ResultWrapper.GenericError(response.code, response.error.message)
            }
        }
}