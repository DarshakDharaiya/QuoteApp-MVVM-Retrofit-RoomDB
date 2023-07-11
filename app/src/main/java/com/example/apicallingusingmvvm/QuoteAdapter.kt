package com.example.apicallingusingmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingusingmvvm.models.Result
import com.example.apicallingusingmvvm.databinding.CellQuoteBinding

class QuoteAdapter(private val quoteList: ArrayList<Result>) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    inner class QuoteViewHolder(private val _binding: CellQuoteBinding) :
        RecyclerView.ViewHolder(_binding.root) {

        fun bind(data: Result) {
            _binding.tvQuote.text = data.content
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = CellQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(quoteList[position])
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }
}