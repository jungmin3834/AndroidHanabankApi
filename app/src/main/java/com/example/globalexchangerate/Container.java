package com.example.globalexchangerate;

import java.util.HashMap;
import java.util.Map;

public class Container {

    private Map<String,RateData> moneyRateList;

    public  Container(){
        moneyRateList = new HashMap<String,RateData>();
    }

    public RateData getRateByName(String name){
        return moneyRateList.get(name);
    }
}
