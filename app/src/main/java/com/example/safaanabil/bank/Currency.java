package com.example.safaanabil.bank;

/**
 * Created by Safaa Nabil on 4/28/2018.
 */

public class Currency {

    private String name;
    private String buy ;
    private  String sell;


    public Currency(String name , String buy , String sell)

    {
        this.name = name;
        this.buy = buy ;
        this.sell = sell;
    }

    public  String getName ()
    {
        return  this.name;

    }

    public String getBuy ()
    {
        return this.buy;
    }

    public  String getSell ()
    {
        return this.sell;
    }
}
