package com.example.wens.repository

import androidx.lifecycle.LiveData
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.status.Resource
import javax.inject.Inject

interface IWensDataSource {

    suspend fun getTopHeadlinesFromCountry(country: String): Resource<BaseListResponse<Articles>>

    fun getTopHeadlinesFromSources(sources: String): LiveData<Resource<BaseListResponse<Articles>>>

    fun getTopHeadlinesFromCategory(category: String): LiveData<Resource<BaseListResponse<Articles>>>

    fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): LiveData<Resource<BaseListResponse<Articles>>>

    fun getTopHeadlinesFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>>

    fun getEverythingFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>>

    fun getEverythingFromQueryInTitle(qInTitle: String): LiveData<Resource<BaseListResponse<Articles>>>

    fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): LiveData<Resource<BaseListResponse<Articles>>>
}