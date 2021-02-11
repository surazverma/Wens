package com.example.wens.pagination

import androidx.paging.PagingSource
import com.example.wens.BuildConfig
import com.example.wens.model.objects.Articles
import com.example.wens.retrofit.WensAPIInterface
import retrofit2.HttpException
import java.io.IOException

const val WENS_STARTING_PAGE_CONSTANT = 1

class WensPagingSource(
    private val wensAPIInterface: WensAPIInterface,
    private val country: String = "us"
) :
    PagingSource<Int, Articles>() {
    private val APIKEY = BuildConfig.API_KEY

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Articles> {
        val position = params.key ?: WENS_STARTING_PAGE_CONSTANT
        val response = wensAPIInterface.getTopHeadlinesFromCountry(country, APIKEY, position)
        return try {
            val articles = response.body()?.data
            LoadResult.Page(
                data = articles!!,
                prevKey = if (position == WENS_STARTING_PAGE_CONSTANT) null else position - 1,
                nextKey = if (articles.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}