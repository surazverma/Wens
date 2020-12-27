package com.example.wens.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.repository.WensRepository
import com.example.wens.status.Resource

class HomeViewModel :ViewModel(){
    val mWensRepository = WensRepository() // TODO: 22/12/20 DI through Dagger

    fun getTopHeadlinesFromCountry(country:String): LiveData<Resource<BaseListResponse<Articles>>> {
        return mWensRepository.getTopHeadlinesFromCountry(country)
    }


}