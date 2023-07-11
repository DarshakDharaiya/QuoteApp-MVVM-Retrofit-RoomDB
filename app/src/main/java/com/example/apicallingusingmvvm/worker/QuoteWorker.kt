package com.example.apicallingusingmvvm.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.apicallingusingmvvm.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context:Context, params: WorkerParameters): Worker(context,params) {

    override fun doWork(): Result {
        val repository = (context as Application).quotesRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBackground()
        }

        return Result.success()
    }
}