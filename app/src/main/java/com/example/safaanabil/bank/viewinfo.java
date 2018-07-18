package com.example.safaanabil.bank;

/**
 * Created by Safaa Nabil on 4/30/2018.
 */

public class viewinfo {
    private String amount ;
    private String Dtime ;
    private  String acumrev;


    public  viewinfo ( String Dtime ,String amount ,String acumrev)
    {
        this.amount = amount ;
        this.Dtime = Dtime ;
        this.acumrev = acumrev ;


    }

    public String getAmount ()
    {
        return  amount;
    }

    public String getDtime ()
    {
        return Dtime;
    }

    public  String getAcumrev ()
    {
        return acumrev;
    }

}
