package com.example.safaanabil.bank;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Transferfragment extends Fragment {
    TextView balance ;
    EditText dnum , anum , amount ;
    final String url1 = "http://192.168.137.1/android/balance.php";
    final String url2 = "http://192.168.137.1/android/trans.php";
    static  String email2;
    double b1 , b2 ;
     View rootview ;
    public Transferfragment() {

    }

    public static  void setEmail (String email)
    {
                email2 = email;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //balance.setText(email);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(Transferinfo.this , response.toString() , Toast.LENGTH_LONG).show();
                            JSONArray object = new JSONArray(response);

                            for(int i = 0 ; i < object.length() ; i++) {
                                JSONObject jsonObject = object.getJSONObject(i);
                                balance.setText(jsonObject.getString("balance") + " E.L.");
                                b1 = Double.parseDouble(balance.getText().toString());
                            }

                        } catch (  Exception e)
                        {
                            Toast.makeText(getActivity() ,e.toString() , Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //Toast.makeText(getActivity() , error.toString() , Toast.LENGTH_LONG).show();

            }
        })  {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email" ,email2);
                return params;
            }
        };

        queue.add(stringRequest);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootview = inflater.inflate(R.layout.activity_transferinfo , null);

        balance = (TextView) rootview.findViewById(R.id.b2);
        dnum = (EditText) rootview.findViewById(R.id.dnum);
        anum = (EditText) rootview.findViewById(R.id.anum);
        amount = (EditText) rootview.findViewById(R.id.amount);
        final Button btn = (Button) rootview.findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               submit(btn);


            }
        });


        return rootview;
    }





    public void submit (View view) {


        if (view.getId() == R.id.submit) {

                           RequestQueue queue = Volley.newRequestQueue(getActivity());
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new
                        Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();


                                } catch (Exception e) {
                                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();

                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                        Calendar calobj = Calendar.getInstance();

                        params.put("idnum", dnum.getText().toString());
                        params.put("acnum", anum.getText().toString());
                        params.put("ltranfer", amount.getText().toString());
                        params.put("time", df.format(calobj.getTime()).toString());
                        return params;
                    }


                };

                queue.add(stringRequest);
            }
        }

    }
