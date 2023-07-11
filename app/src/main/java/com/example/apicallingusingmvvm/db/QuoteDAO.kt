package com.example.apicallingusingmvvm.db

import com.example.apicallingusingmvvm.models.Result
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface QuoteDAO {

    @Insert
    suspend fun addQuotes(quotes : List<Result>)

    @Query("SELECT * FROM quote")
    suspend fun getQuotes(): List<Result>

}