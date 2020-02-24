package com.example.globalexchangerate;

public class RateData
{
    private String Name;
    private String Rate;
    private float Money;


    public RateData(String _name ,float _money,String _rate){
        Money = _money;
        Name = _name;
        Rate = _rate;
    }

    public float getMoney(){
        return Money;
    }

    public String getName(){
        return Name;
    }

    public String getRate(){
        return Rate;
    }
};