package com.example.wens.retrofit

import com.example.wens.constants.ApiConstants
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WensAPIInterface {
    //TOP HEADLINES SECTION
    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    fun getTopHeadlinesFromCountry(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    fun getTopHeadLinesFromSources(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    fun getTopHeadlinesFromCategory(
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    fun getTopHeadlinesFromCountryInCategory(
        @Query("category") category: String,
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    @GET(ApiConstants.API_HEADER_TOP_HEADLINES)
    fun getTopHeadlinesFromQuery(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    //EVERYTHING SECTION
    @GET(ApiConstants.API_HEADER_EVERYTHING)
    fun getEverythingByQuery(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    @GET(ApiConstants.API_HEADER_EVERYTHING)
    fun getEverythingByQueryInTitle(
        @Query("qInTitle") qInTitle: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    @GET(ApiConstants.API_HEADER_EVERYTHING)
    fun getEverythingByQueryAndDate(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy:String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>


}

