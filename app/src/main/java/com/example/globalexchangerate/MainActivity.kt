package com.example.globalexchangerate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    val container = Container(HashMap())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        Server(container,"http://172.30.1.17:8080/getMoneyRate").execute()

        beforeTransfer_et.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(beforeTransfer_et.text.toString().length == 0)
                    return
                afterTransfer_tv.text = calculateMoneyRate(beforeTransfer_et.text.toString()) + " " + afterCountry_tv.text
            }
        })
    }


    fun calculateMoneyRate(money : String): String {
        var m = StringBuffer(money)
        var r = container.moneyRateList.get(beforeTransfer_Tv.text.toString())?.money
        var rate: Int
        if( r != null){
            var ratestr = StringBuffer("")
            var count = 0
            for(s in  r.toString()) {
                if(s != '.') {
                    ratestr.append(s)
                    count++
                }
                else
                    count = 0
            }
            if(count ==  r.toString().length)
                count = 0
            rate = ratestr.toString().toInt()

            var num = 0
            for (i in m.length - 1 downTo 0) {
                num += (m[i] - '0') * rate
                m.setCharAt(i ,if (num > 9) (num % 10 + '0'.toInt()).toChar() else (num + '0'.toInt()).toChar())
                num /= 10
            }

            while (num > 0) {
                m.insert(0,(num % 10 + '0'.toInt()).toChar())
                num /= 10
            }
            m.insert(m.length- count , '.')
            return m.toString()
        }
        return ""
    }

    fun clickOnTransferMoney(view: View) {
        Log.v("메세지 : " , beforeTransfer_Tv.text.toString())
        if(beforeTransfer_et.text.toString().length == 0)
            return
        afterTransfer_tv.text = calculateMoneyRate(beforeTransfer_et.text.toString()) + " " +
                afterCountry_tv.text
    }
}
