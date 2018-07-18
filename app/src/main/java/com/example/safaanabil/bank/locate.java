package com.example.safaanabil.bank;

/**
 * Created by Safaa Nabil on 4/27/2018.
 */

public class locate {

    private double lat , lang ;

    public locate(double lat , double lang)
    {
        this.lat = lat;
        this.lang = lang ;

    }

    public double getLat ()
    {
        return lat;
    }

    public double getLang ()
    {
        return  lang ;
    }
}
