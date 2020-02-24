package com.example.globalexchangerate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.net.URL


class MainActivity : AppCompatActivity() {

    val container = Container(HashMap())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        Server().getRateDataFromServer(container)
    }

    fun clickOnTransferMoney(view: View) {

    }
}
