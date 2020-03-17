package com.example.globalexchangerate

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {


    val container = Container(HashMap())
    val calculate = Calculate()
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
                transferMoney()
        }
        })

        beforeTransfer_sp?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                transferMoney()
            }
        }

        afterCountry_sp?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                transferMoney()
            }
        }

        beforeTransfer_sp.setSelection(46)
    }

    fun getCalculateString() : String{
        var a = 1f
        if(getRateSplit(beforeTransfer_sp.selectedItem.toString()) != "KRW")
            a =container.moneyRateList.get(getRateSplit(beforeTransfer_sp.selectedItem.toString()))?.money ?:1 as Float
        var b = 1f
        if(getRateSplit(afterCountry_sp.selectedItem.toString()) != "KRW")
            b =container.moneyRateList.get(getRateSplit(afterCountry_sp.selectedItem.toString()))?.money?:1 as Float

       return calculate.calculateMoneyRate(beforeTransfer_et.text.toString(),
           (a / b) )
    }

    fun getRateSplit(countryRate : String) : String{
      var str = StringBuilder(countryRate)
      return "" + str.get(str.length - 3)  + str.get(str.length - 2)  + str.get(str.length - 1)
    }
    fun transferMoney(){
        if(beforeTransfer_et.text.toString().length == 0)
            afterTransfer_tv.text = "0 " +" "+    afterCountry_sp.selectedItem.toString()
        else
            afterTransfer_tv.text = getCalculateString() +" "+  afterCountry_sp.selectedItem.toString()
    }

    fun clickOnTransferMoney(view: View) {
        transferMoney()
    }
}
