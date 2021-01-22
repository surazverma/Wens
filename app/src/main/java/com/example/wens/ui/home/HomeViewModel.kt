package com.example.wens.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wens.model.objects.Articles
import com.example.wens.repository.WensRepository
import com.example.wens.util.ResultWrapper
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(val wensRepository: WensRepository) : ViewModel() {

    val news = MutableLiveData<ResultWrapper<List<Articles>>>()

    fun getTopHeadlinesFromC(country: String) {
        news.value = ResultWrapper.Loading
        viewModelScope.launch {
            when (val response = wensRepository.getTopHeadlinesFromCountry(country)) {
                is ResultWrapper.Success -> news.value =
                    ResultWrapper.Success(filterArticleByContent(response.value.data!!))
                is ResultWrapper.NetworkError -> news.value =
                    ResultWrapper.NetworkError(response.error)
                is ResultWrapper.ServerError -> news.value =
                    ResultWrapper.ServerError(response.code, response.error)
                is ResultWrapper.GenericError -> news.value =
                    ResultWrapper.GenericError(response.code, response.error)
            }
        }
    }

    private fun filterArticleByContent(list: List<Articles>): List<Articles> {

        val mutableListOfArticles = mutableListOf<Articles>()
        list.forEach { article ->
            article.urlToImage?.let {
                mutableListOfArticles.add(article)
            }
        }

        return mutableListOfArticles
    }
}