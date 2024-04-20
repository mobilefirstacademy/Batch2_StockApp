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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse
import java.io.IOException

class TempStockListFragment : Fragment() {
    private lateinit var rvStockList : RecyclerView
    private val apiKey = "UOwG8c78cY5kVLuoEke3gu5JK2i8IGyK"

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

        fetchDataWithCoroutines(apiKey)
    }
    suspend fun fetchStockList(apiKey: String): MutableList<StockModel>? {
        return try {
            val response = StockProvider.stockApi.getStockList(apiKey).awaitResponse()

            if (response.isSuccessful) {
                response.body()
            } else {
                Log.e("Stock", "${response.code()}:\n${response.errorBody()?.string()}")
                null
            }
        } catch (e: IOException) {
            Log.e("Stock", "Ошибка при выполнении запроса: ${e.message}")
            Toast.makeText(
                requireContext(),
                "Ошибка при выполнении запроса: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
            null
        }
    }

    private fun handleStockListResult(result: MutableList<StockModel>?) {
        result?.let {
            setStockListAdapter(it)
        } ?: run {
            Log.e("Stock", "Не удалось получить список акций")
        }
    }

    private fun fetchDataWithCoroutines(apiKey: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val result = fetchStockList(apiKey)
            handleStockListResult(result)
        }
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