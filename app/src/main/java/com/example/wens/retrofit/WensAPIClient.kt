package com.example.wens.retrofit

import com.example.wens.BuildConfig
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WensAPIClient {
    var retrofit: Retrofit? = null
    private const val API_BASE_URL = BuildConfig.BASE_URL

    fun getClient(): WensAPIInterface {

        val logging = HttpLoggingInterceptor()
        logging.level = Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        return createInterface()
    }

    private fun createInterface(): WensAPIInterface {
        return retrofit!!.create(WensAPIInterface::class.java)
    }
}