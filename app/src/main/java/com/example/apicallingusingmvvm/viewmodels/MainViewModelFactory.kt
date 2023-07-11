package com.example.apicallingusingmvvm.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apicallingusingmvvm.repository.QuotesRepository

class MainViewModelFactory(private val quotesRepository: QuotesRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quotesRepository) as T
    }
}