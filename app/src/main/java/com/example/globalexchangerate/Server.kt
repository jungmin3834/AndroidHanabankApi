package com.example.globalexchangerate

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class Server(){
    fun getRateDataFromServer(container : Container){
        val task = Runnable {
            val jsonStr = URL("http://172.30.1.17:8080/getMoneyRate").readText()
            var js = JSONArray(jsonStr)
            for (i in 0 until js.length()) {
                val item = js.getJSONObject(i)
                container.moneyRateList.getOrPut(item.get("name").toString()){ RateData(
                    item.get("name").toString(),
                    item.get("rate").toString(),
                    item.get("money").toString().toFloat())
                }
            }
            Log.v("성공 여부",jsonStr)
        }
        task.run()
    }
}
