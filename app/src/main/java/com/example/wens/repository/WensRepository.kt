package com.example.wens.repository

import androidx.lifecycle.LiveData
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.operation.remote.WensNetworkOperation
import com.example.wens.status.Resource

class WensRepository {
    val mWensNetworkOperation = WensNetworkOperation

    fun getTopHeadlinesFromCountry(country: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensNetworkOperation.getTopHeadlinesFromCountry(country)
    }
}