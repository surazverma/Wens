package com.example.wens.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.repository.WensRepository
import com.example.wens.status.Resource

class HomeViewModel constructor(val wensRepository: WensRepository) : ViewModel() {


    fun getTopHeadlinesFromCountry(country: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRepository.getTopHeadlinesFromCountry(country)
    }


}