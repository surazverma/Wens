package com.example.wens.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.wens.model.objects.Articles
import com.example.wens.repository.WensRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(val wensRepository: WensRepository) : ViewModel() {

    lateinit var pagingNews: Flow<PagingData<Articles>>

    fun getTopStreamHeadlinesFromCountry(country: String) {
        viewModelScope.launch {
            pagingNews = wensRepository
                .getTopHeadlinesStreamFromCountry(country)
                .cachedIn(viewModelScope)
        }
    }

}