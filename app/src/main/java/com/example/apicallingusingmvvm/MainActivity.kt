package com.example.apicallingusingmvvm

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.LogPrinter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apicallingusingmvvm.api.QuoteService
import com.example.apicallingusingmvvm.api.RetrofitHelper
import com.example.apicallingusingmvvm.models.Result
import com.example.apicallingusingmvvm.databinding.ActivityMainBinding
import com.example.apicallingusingmvvm.repository.QuotesRepository
import com.example.apicallingusingmvvm.viewmodels.MainViewModel
import com.example.apicallingusingmvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var quoteAdapter: QuoteAdapter
    private var quoteList = ArrayList<Result>()

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = (application as Application).quotesRepository

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(repository)
        )[MainViewModel::class.java]

        mainViewModel.quotes.observe(this, Observer {
            it.results.let { quoteList.addAll(it) }
            quoteAdapter(quoteList)
        })

    }

    private fun quoteAdapter(quoteData: ArrayList<Result>) {
        binding.rvQuote.apply {
            quoteAdapter = QuoteAdapter(quoteData)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = quoteAdapter
        }
    }

}