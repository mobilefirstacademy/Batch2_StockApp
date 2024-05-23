package com.example.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.StockModel
import com.squareup.picasso.Picasso

class StockListAdapter (
    private val stockList: List<StockModel>
) : RecyclerView.Adapter<StockListAdapter.StockListViewHolder>(){
    class StockListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stockName: TextView = view.findViewById(R.id.tv_stock_name)
        val stockLogo: ImageView = view.findViewById(R.id.iv_stock_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockListViewHolder {
        val stockItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.stock_item, parent, false)
        return StockListViewHolder(stockItemView)
    }

    override fun getItemCount(): Int = stockList.size

    override fun onBindViewHolder(holder: StockListViewHolder, position: Int) {
        holder.stockName.text = "${stockList[position].symbol}"
        val url = "https://financialmodelingprep.com/image-stock/${stockList[position].symbol}.png"
        Picasso.get().load(url).into(holder.stockLogo)
    }
}