package com.example.wens.retrofit

import com.example.wens.constants.ApiConstants
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.model.responses.ErrorResponse
import com.haroldadmin.cnradapter.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WensAPIInterface {
    //TOP HEADLINES SECTION
    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    suspend fun getTopHeadlinesFromCountry(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
    ): Response<BaseListResponse<Articles>>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    suspend fun getTopHeadLinesFromSources(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): NetworkResponse<BaseListResponse<Articles>, ErrorResponse>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    suspend fun getTopHeadlinesFromCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): NetworkResponse<BaseListResponse<Articles>, ErrorResponse>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    suspend fun getTopHeadlinesFromCountryInCategory(
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): NetworkResponse<BaseListResponse<Articles>, ErrorResponse>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    suspend fun getTopHeadlinesFromQuery(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NetworkResponse<BaseListResponse<Articles>, ErrorResponse>

    //EVERYTHING SECTION
    @GET(ApiConstants.API_HEADER_EVERYTHING)
    suspend fun getEverythingByQuery(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NetworkResponse<BaseListResponse<Articles>, ErrorResponse>

    @GET(ApiConstants.API_HEADER_EVERYTHING)
    suspend fun getEverythingByQueryInTitle(
        @Query("qInTitle") qInTitle: String,
        @Query("apiKey") apiKey: String
    ): NetworkResponse<BaseListResponse<Articles>, ErrorResponse>

    @GET(ApiConstants.API_HEADER_EVERYTHING)
    suspend fun getEverythingByQueryAndDate(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): NetworkResponse<BaseListResponse<Articles>, ErrorResponse>


}

