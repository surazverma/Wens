package com.example.wens.operation.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
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
import kotlinx.coroutines.flow.flow


object WensRemoteOperation : IWensDataSource {
    private const val apiKey = BuildConfig.API_KEY

    override suspend fun getTopHeadlinesFromCountry(country: String): Flow<ResultWrapper<BaseListResponse<Articles>>> {
        return flow {
//            emit(
////                handleResponseResult(
//////                    WensAPIClient.getClient().getTopHeadlinesFromCountry(country, apiKey, 1)
////                )
//            )
        }
    }

    fun getTopHeadlineFromCountryStream(country: String) = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 60, enablePlaceholders = false),
        pagingSourceFactory = { WensPagingSource(WensAPIClient.getClient(), country) }
    ).liveData

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