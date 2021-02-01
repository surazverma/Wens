package com.example.wens.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wens.model.objects.Articles
import com.example.wens.repository.WensRepository
import com.example.wens.util.ResultWrapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(val wensRepository: WensRepository) : ViewModel() {

    val news = MutableLiveData<ResultWrapper<List<Articles>>>()

    fun getTopHeadlinesFromC(country: String) {
        news.value = ResultWrapper.Loading
        viewModelScope.launch {
            wensRepository.getTopHeadlinesFromCountry(country).collect {
                when (it) {
                    is ResultWrapper.Success -> news.value =
                        ResultWrapper.Success(filterArticleByContent(it.value.data!!))
                    is ResultWrapper.NetworkError -> news.value =
                        ResultWrapper.NetworkError(it.error)
                    is ResultWrapper.ServerError -> news.value =
                        ResultWrapper.ServerError(it.code, it.error)
                    is ResultWrapper.GenericError -> news.value =
                        ResultWrapper.GenericError(it.code, it.error)
                }
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