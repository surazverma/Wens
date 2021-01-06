package com.example.wens.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wens.model.objects.Articles
import com.example.wens.model.responses.BaseListResponse
import com.example.wens.repository.WensRepository
import com.example.wens.status.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel constructor(val wensRepository: WensRepository) : ViewModel() {


//    fun getTopHeadlinesFromCountry(country: String): LiveData<Resource<BaseListResponse<Articles>>> {
//        return wensRepository.getTopHeadlinesFromCountry(country)
//    }

    val news = MutableLiveData<Resource<List<Articles>>>()

    fun getTopHeadlinesFromC(country: String) {
        news.value = Resource.Loading()
        viewModelScope.launch {
            try {
                news.value =
                    Resource.Success(wensRepository.getTopHeadlinesFromCountry(country).data?.data!!)
            } catch (e: Exception) {
                news.value = Resource.Error(e.toString())
            }
        }
    }
}