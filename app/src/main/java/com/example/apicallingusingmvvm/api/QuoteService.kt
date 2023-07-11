package com.example.apicallingusingmvvm.api

import com.example.apicallingusingmvvm.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getQuote(@Query("page") Page: Int): Response<QuoteList>

}