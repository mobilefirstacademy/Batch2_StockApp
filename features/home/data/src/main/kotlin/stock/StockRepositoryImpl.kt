package stock

import android.util.Log
import com.example.data.stock.StockProvider
import entities.StockModel
import repositories.StockRepository
import java.io.IOException
import kotlin.concurrent.thread

class StockRepositoryImpl : StockRepository {
    override fun filterStocksByQuery(callback: (List<StockModel>?) -> Unit) {
        try {
            thread{
                val response = StockProvider.stockApi.getStockList(apiKey).execute()
                callback(response.body())
            }
        } catch (e: IOException) {
            Log.e("Stock", "Ошибка при выполнении запроса: ${e.message}")
        }
    }
}