package com.example.apicallingusingmvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apicallingusingmvvm.models.QuoteList
import com.example.apicallingusingmvvm.repository.QuotesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val quotesRepository: QuotesRepository): ViewModel() {

    init {
        viewModelScope.launch {
            quotesRepository.getQuotes(1)
        }
    }

    val quotes : LiveData<QuoteList>
    get() = quotesRepository.quotes

}