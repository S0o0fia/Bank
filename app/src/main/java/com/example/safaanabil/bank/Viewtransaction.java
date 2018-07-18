package com.example.safaanabil.bank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Viewtransaction extends AppCompatActivity {

    RecyclerView recyclerView;
    Viewadapter adapter;
    ArrayList<viewinfo> data = new ArrayList<>();
    ArrayList<viewinfo> viewinfos = new ArrayList<>();
    final String url = "http://192.168.137.1/android/viewtrans.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtransaction);
        recyclerView = (RecyclerView) findViewById(R.id.transaction);
        recieve ();


    }



    public void recieve ()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Toast.makeText(Exrate.this , response.toString() , Toast.LENGTH_LONG).show();
                            JSONArray results=new JSONArray(response);
                            for(int i=0;i<results.length();i++)
                            {
                                JSONObject cur=results.getJSONObject(i);
                               viewinfo newview=new viewinfo(


                                        cur.getString("timedate"),
                                        cur.getString("amount") ,
                                        cur.getString("rcvacnum")

                                );
                                viewinfos.add(newview);
                            }
                            adapter = new Viewadapter( viewinfos , getApplicationContext());
                            recyclerView.setAdapter(adapter);
                            RecyclerView.LayoutManager layout = new GridLayoutManager(getApplicationContext() , 1);
                            recyclerView.setLayoutManager(layout);
                        } catch (  JSONException e)
                        {
                            Toast.makeText(Viewtransaction.this ,e.toString() , Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Viewtransaction.this , error.toString() , Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email" ,getIntent().getStringExtra("email"));

                return params;
            }



        };

        queue.add(stringRequest);
    }



}
