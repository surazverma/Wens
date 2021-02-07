package com.example.wens

import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.model.responses.ErrorResponse
import com.example.wens.retrofit.WensAPIClient
import com.haroldadmin.cnradapter.NetworkResponse
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test


class WensApiTesting {

    private val apiClient = WensAPIClient.getClient()
    private val apiKey = BuildConfig.API_KEY

//    @Test
//    fun `Get Top Headline From Country`() = runBlocking {
//        val response = apiClient.getTopHeadlinesFromCountry("us", apiKey,1)
//        responseHandler(response)
//    }

    @Test
    fun `Get Top Headline From Category`() = runBlocking {
        val response = apiClient.getTopHeadlinesFromCategory("politics", apiKey)
        responseHandler(response)
    }

//    @Test
//    fun `Get Top Headline From Tags`() = runBlocking {
//        val response = apiClient.getTopHeadlinesFromCountry("us", apiKey)
//        responseHandler(response)
//    }

    @Test
    fun `Get Top Headline From Query`() = runBlocking {
        val response = apiClient.getTopHeadlinesFromQuery("bitcoin", apiKey)
        responseHandler(response)
    }

    @Test
    fun `Get Top Headline From Country In Category`() = runBlocking {
        val response = apiClient.getTopHeadlinesFromCountryInCategory("politics", "in", apiKey)
        responseHandler(response)
    }

    @Test
    fun `Get Top Headline From Sources`() = runBlocking {
        val response = apiClient.getTopHeadLinesFromSources("al-jazeera-english", apiKey)
        responseHandler(response)
    }

    private fun responseHandler(response: NetworkResponse<BaseListResponse<Articles>, ErrorResponse>) {
        when (response) {
            is NetworkResponse.Success -> {
                assertNotNull(response.body.data)
            }
            is NetworkResponse.NetworkError -> {
                assertNotNull(response.error)
            }
            is NetworkResponse.ServerError -> {
                assertNotNull(response.body?.message)
            }
            is NetworkResponse.UnknownError -> {
                assertNotNull(response.error)
            }
        }
    }

}