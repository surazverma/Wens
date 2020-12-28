package com.example.wens.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.repository.IWensRepository
import com.example.wens.repository.WensRepository
import com.example.wens.status.Resource
import com.example.wens.ui.base.BaseViewModel

class HomeViewModel(private val wensRepository: IWensRepository) : BaseViewModel() {
    // TODO: 22/12/20 DI through Dagger



    fun getTopHeadlinesFromCountry(country: String): LiveData<Resource<BaseListResponse<Articles>>> {
        return wensRepository.getTopHeadlinesFromCountry(country)
    }


}