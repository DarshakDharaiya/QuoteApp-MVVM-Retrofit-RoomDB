package com.example.apicallingusingmvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apicallingusingmvvm.api.QuoteService
import com.example.apicallingusingmvvm.db.QuoteDAO
import com.example.apicallingusingmvvm.db.QuoteDatabase
import com.example.apicallingusingmvvm.models.QuoteList
import com.example.apicallingusingmvvm.utils.NetworkUtils

class QuotesRepository(
    private val quoteService: QuoteService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quoteLiveDate = MutableLiveData<QuoteList>()
    val quotes: LiveData<QuoteList>
        get() = quoteLiveDate

    suspend fun getQuotes(page: Int) {

        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val result = quoteService.getQuote(page)
            if (result.isSuccessful && result.body() != null) {
                quoteDatabase.quoteDAO().addQuotes(result.body()!!.results)
                quoteLiveDate.postValue(result.body())
            }
        } else {
            val quotes = quoteDatabase.quoteDAO().getQuotes()
            val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
            quoteLiveDate.postValue(quoteList)
        }


    }

    suspend fun getQuotesBackground(){
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuote(randomNumber)
        if (result.isSuccessful && result.body() != null) {
            quoteDatabase.quoteDAO().addQuotes(result.body()!!.results)
        }
    }

}