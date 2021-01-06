package com.example.wens.operation.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wens.BuildConfig
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.repository.IWensDataSource
import com.example.wens.retrofit.WensAPIClient
import com.example.wens.status.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object WensRemoteOperation : IWensDataSource {
    val apiKey = "a68be71781264398a4061a468f462fc9"

    override suspend fun getTopHeadlinesFromCountry(country: String): Resource<BaseListResponse<Articles>> {
        val response =  WensAPIClient.getClient().getTopHeadlinesFromCountry(country, apiKey)
        return if (response.isSuccessful){
            Resource.Success(response.body()!!)
        }else{
            Resource.Error(response.message())
        }
    }

    override fun getTopHeadlinesFromSources(sources: String): LiveData<Resource<BaseListResponse<Articles>>> {
        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call = WensAPIClient.getClient().getTopHeadLinesFromSources(sources, apiKey)
        call.enqueue(object : Callback<BaseListResponse<Articles>> {

            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful) {
                    mldData.value = Resource.Success(response.body()!!)
                } else {
                    mldData.value = Resource.Error(response.body()?.message ?: response.message())
                }
            }
        })

        return mldData
    }

    override fun getTopHeadlinesFromCategory(category: String): LiveData<Resource<BaseListResponse<Articles>>> {
        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call = WensAPIClient.getClient().getTopHeadLinesFromSources(category, apiKey)
        call.enqueue(object : Callback<BaseListResponse<Articles>> {

            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful) {
                    mldData.value = Resource.Success(response.body()!!)
                } else {
                    mldData.value = Resource.Error(response.body()?.message ?: response.message())
                }
            }
        })

        return mldData
    }


    override fun getTopHeadlinesFromCategoryInCountry(
        category: String,
        country: String
    ): LiveData<Resource<BaseListResponse<Articles>>> {
        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call = WensAPIClient.getClient()
            .getTopHeadlinesFromCountryInCategory(category, country, apiKey)
        call.enqueue(object : Callback<BaseListResponse<Articles>> {

            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful) {
                    mldData.value = Resource.Success(response.body()!!)
                } else {
                    mldData.value = Resource.Error(response.body()?.message ?: response.message())
                }
            }
        })

        return mldData
    }

    override fun getTopHeadlinesFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>> {
        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call = WensAPIClient.getClient().getTopHeadlinesFromQuery(query, apiKey)
        call.enqueue(object : Callback<BaseListResponse<Articles>> {

            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful) {
                    mldData.value = Resource.Success(response.body()!!)
                } else {
                    mldData.value = Resource.Error(response.body()?.message ?: response.message())
                }
            }
        })

        return mldData
    }

    override fun getEverythingFromQuery(query: String): LiveData<Resource<BaseListResponse<Articles>>> {
        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call = WensAPIClient.getClient().getEverythingByQuery(query, apiKey)
        call.enqueue(object : Callback<BaseListResponse<Articles>> {

            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful) {
                    mldData.value = Resource.Success(response.body()!!)
                } else {
                    mldData.value = Resource.Error(response.body()?.message ?: response.message())
                }
            }
        })

        return mldData
    }

    override fun getEverythingFromQueryInTitle(queryInTitle: String): MutableLiveData<Resource<BaseListResponse<Articles>>> {
        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call = WensAPIClient.getClient().getEverythingByQueryInTitle(queryInTitle, apiKey)
        call.enqueue(object : Callback<BaseListResponse<Articles>> {

            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful) {
                    mldData.value = Resource.Success(response.body()!!)
                } else {
                    mldData.value = Resource.Error(response.body()?.message ?: response.message())
                }
            }
        })

        return mldData
    }

    override fun getEverythingFromQueryAndDate(
        q: String,
        from: String,
        to: String,
        sortBy: String
    ): MutableLiveData<Resource<BaseListResponse<Articles>>> {
        val mldData = MutableLiveData<Resource<BaseListResponse<Articles>>>()
        mldData.value = Resource.Loading()

        val call =
            WensAPIClient.getClient().getEverythingByQueryAndDate(q, from, to, sortBy, apiKey)
        call.enqueue(object : Callback<BaseListResponse<Articles>> {

            override fun onFailure(call: Call<BaseListResponse<Articles>>, t: Throwable) {
                mldData.value = Resource.Error()
            }

            override fun onResponse(
                call: Call<BaseListResponse<Articles>>,
                response: Response<BaseListResponse<Articles>>
            ) {
                if (response.isSuccessful) {
                    mldData.value = Resource.Success(response.body()!!)
                } else {
                    mldData.value = Resource.Error(response.body()?.message ?: response.message())
                }
            }
        })

        return mldData
    }


}