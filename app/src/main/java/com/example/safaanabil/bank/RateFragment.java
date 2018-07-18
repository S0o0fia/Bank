package com.example.safaanabil.bank;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class RateFragment extends Fragment {
    RecyclerView recyclerView;
    Curadapter curadapter;
    ArrayList<Currency> data = new ArrayList<>();
    ArrayList<Currency> currencies = new ArrayList<>();
    final String url = "http://192.168.137.1/android/rate.php";



    public RateFragment() {
        // Required empty public constructor
    }

    public static RateFragment newInstance() {
        RateFragment fragment = new RateFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // View root = inflater.inflate(R.layout.cur_pos , container , false);
       // recyclerView = (RecyclerView) rootView.findViewById(R.id.list);
        recieve ();
        return inflater.inflate(R.layout.activity_exrate, null);
    }




    public void recieve ()
    {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // Toast.makeText(Exrate.this , response.toString() , Toast.LENGTH_LONG).show();
                            JSONArray results=new JSONArray(response);
                            for(int i=0;i<results.length();i++)
                            {
                                JSONObject cur=results.getJSONObject(i);
                                Currency newcur=new Currency(

                                        cur.getString("name") ,
                                        cur.getString("buy"),
                                        cur.getString("sell")

                                );
                                currencies.add(newcur);
                            }
                            recyclerView = (RecyclerView) getActivity().findViewById(R.id.list);
                            curadapter = new Curadapter(currencies , getContext());
                            recyclerView.setAdapter(curadapter);
                            RecyclerView.LayoutManager layout = new GridLayoutManager(getContext() , 1);
                            recyclerView.setLayoutManager(layout);
                        } catch (  JSONException e)
                        {
                            Toast.makeText(getActivity() ,e.toString() , Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity() , error.toString() , Toast.LENGTH_LONG).show();

            }
        });

        queue.add(stringRequest);
    }





}
