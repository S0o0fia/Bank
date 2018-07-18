package com.example.safaanabil.bank;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;

/**
 * Created by Safaa Nabil on 5/2/2018.
 */

public class Menufragment extends android.support.v4.app.Fragment {



    public  static  String email2 ;
    public static  void setEmail (String email)
    {
        email2 = email;
    }
    public TextView btn1 ,btn2 , btn3 , btn4;

    public Menufragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View rootview = inflater.inflate(R.layout.activity_menu , null);

       btn1 =  (TextView) rootview.findViewById(R.id.profiletext);
       btn1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i3 = new Intent(getActivity(),Profile.class);
               i3.putExtra("email" ,email2);
               startActivity(i3);
           }
       });



        btn2 =  (TextView) rootview.findViewById(R.id.transtext);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity() , Viewtransaction.class);
                i.putExtra("email" , email2);
                startActivity(i);
            }
        });

        btn3 =  (TextView) rootview.findViewById(R.id.calltext);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3 = new Intent(getActivity() , MapsActivity.class );
                startActivity(i3);


            }
        });

        btn4 =  (TextView) rootview.findViewById(R.id.Logout);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity() , MainActivity.class);
                 startActivity(i);

            }
        });


        return rootview;
    }



}