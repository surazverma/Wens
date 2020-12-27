package com.example.wens.retrofit

import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WensAPIInterface {

    @GET("/v2/top-headlines")
    fun getTopHeadlinesFromCountry(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<BaseListResponse<Articles>>

    // TODO: 22/12/20 other types of api calls are based on category | sources | search query

}

