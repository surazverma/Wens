package com.example.wens.retrofit

import com.example.wens.BuildConfig
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WensAPIClient {
    var retrofit: Retrofit? = null
    private val API_BASE_URL = BuildConfig.BASE_URL

    fun getClient(): WensAPIInterface {

        retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // return the interface by creating one.
        return createInterface()
    }

    private fun createInterface(): WensAPIInterface {
        return retrofit!!.create(WensAPIInterface::class.java)
    }
}