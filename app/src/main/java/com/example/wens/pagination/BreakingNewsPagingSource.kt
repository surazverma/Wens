package com.example.wens.pagination

import androidx.paging.PagingSource
import com.example.wens.BuildConfig
import com.example.wens.model.objects.Articles
import com.example.wens.retrofit.WensAPIInterface
import com.haroldadmin.cnradapter.NetworkResponse

const val WENS_STARTING_PAGE_CONSTANT = 1

class WensPagingSource(
    private val wensAPIInterface: WensAPIInterface,
    private val country: String = "us"
) :
    PagingSource<Int, Articles>() {
    private val APIKEY = BuildConfig.API_KEY

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        val position = params.key ?: WENS_STARTING_PAGE_CONSTANT
        val networkResponse = wensAPIInterface.getTopHeadlinesFromCountry(country, APIKEY, position)
        when (networkResponse) {
            is NetworkResponse.Success -> {
                val articles = networkResponse.body.data
                return LoadResult.Page(
                    data = if (!articles.isNullOrEmpty()) articles else listOf(),
                    prevKey = if (position == WENS_STARTING_PAGE_CONSTANT) null else position - 1,
                    nextKey = if (articles.isNullOrEmpty()) null else position + 1
                )
            }
            is NetworkResponse.ServerError -> {
                return LoadResult.Page(
                    data = listOf(),
                    prevKey = null,
                    nextKey = null
                )
            }
            is NetworkResponse.NetworkError -> return LoadResult.Error(networkResponse.error) // TODO: 16/2/21 create an exception for server which is thrown to detect that there is a server error.
            is NetworkResponse.UnknownError -> return LoadResult.Error(networkResponse.error)
        }
    }


}