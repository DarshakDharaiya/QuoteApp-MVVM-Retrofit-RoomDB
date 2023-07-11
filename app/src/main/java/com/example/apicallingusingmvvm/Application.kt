package com.example.apicallingusingmvvm

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.apicallingusingmvvm.api.QuoteService
import com.example.apicallingusingmvvm.api.RetrofitHelper
import com.example.apicallingusingmvvm.db.QuoteDatabase
import com.example.apicallingusingmvvm.repository.QuotesRepository
import com.example.apicallingusingmvvm.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class Application : Application() {

    lateinit var quotesRepository: QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setUpWorker()
    }

    private fun setUpWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java, 30, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
        WorkManager.getInstance(this).enqueue(workRequest)
    }

    private fun initialize() {
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val database = QuoteDatabase.getQuote(applicationContext)
        quotesRepository = QuotesRepository(quoteService, database, applicationContext)
    }

}