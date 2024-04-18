package com.example.stockgetter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TempStockListFragment : Fragment() {
    private var stockList : MutableList<StockModel>? = null
    private lateinit var rvStockList : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_temp_stock_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvStockList = view.findViewById(R.id.rv_stock_list)
        rvStockList.layoutManager = LinearLayoutManager(requireContext())

        fetchStockList()
    }

    private fun fetchStockList() {
        val apiKey = "UOwG8c78cY5kVLuoEke3gu5JK2i8IGyK"
        val call: Call<MutableList<StockModel>> = StockProvider.stockApi.getStockList(apiKey)

        call.enqueue(object : Callback<MutableList<StockModel>> {
            override fun onResponse(
                call: Call<MutableList<StockModel>>,
                response: Response<MutableList<StockModel>>
            ) {
                if (response.isSuccessful) {
                    stockList = response.body()
                    setStockListAdapter(stockList)
                } else {
                    Log.e("Stock", "${response.code()}:\n${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<MutableList<StockModel>>, t: Throwable) {
                Log.e("MainActivity", "Ошибка при выполнении запроса: ${t.message}")
                Toast.makeText(
                    requireContext(),
                    "Ошибка при выполнении запроса: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun setStockListAdapter(stockList: MutableList<StockModel>?) {
        if (stockList != null) {
            val stockListAdapter = StockListAdapter(stockList!!)
            rvStockList.adapter = stockListAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TempStockListFragment()
    }
}