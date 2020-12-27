package com.example.wens.operation.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wens.BuildConfig
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.retrofit.WensAPIClient
import com.example.wens.status.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object WensNetworkOperation {
    val apiKey =
        BuildConfig.API_KEY

    fun getTopHeadlinesFromCountry(country: String): LiveData<Resource<BaseListResponse<Articles>>> {

        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call = WensAPIClient.getClient().getTopHeadlinesFromCountry(country, apiKey)


        call.enqueue(object : Callback<BaseListResponse<Articles>> {
            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful){
                    mldData.value = Resource.Success(response.body()!!)
                }else{
                    mldData.value = Resource.Error(response.message())
                }

            }

        })

        return mldData
    }
}