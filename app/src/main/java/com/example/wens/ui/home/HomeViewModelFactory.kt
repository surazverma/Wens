package com.example.wens.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wens.repository.WensRepository

class HomeViewModelFactory(val wensRepository: WensRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(wensRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}