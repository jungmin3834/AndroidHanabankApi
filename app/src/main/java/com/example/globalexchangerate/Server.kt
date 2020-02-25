package com.example.globalexchangerate

import android.os.AsyncTask
import android.util.Log
import org.json.JSONArray
import java.net.URL


class Server(container : Container, path : String) : AsyncTask<Void, Void, Void>() {
    val container = container
    val path = path

    override fun doInBackground(vararg params: Void?): Void? {
        getRateDataFromServer()
        return null
    }

    fun getRateDataFromServer(){
        val task = Runnable {
            val jsonStr = URL(path).readText()
            var js = JSONArray(jsonStr)
            Log.v("성공 여부",jsonStr)
            for (i in 0 until js.length()) {
                val item = js.getJSONObject(i)
                container.moneyRateList.getOrPut(item.get("name").toString()){ RateData(
                    item.get("name").toString(),
                    item.get("rate").toString(),
                    item.get("money").toString().toFloat())
                }
            }

        }
        task.run()
    }

}





