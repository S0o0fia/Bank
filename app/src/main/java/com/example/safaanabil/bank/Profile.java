package com.example.safaanabil.bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    final String url1  = "http://192.168.137.1/android/profile.php";
    final String url2  = "http://192.168.137.1/android/profile2.php";

    TextView fname , lname , email , idnum , acnum , address , phone , balance;
     String email2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email2 = getIntent().getStringExtra("email");
        fname = (TextView) findViewById(R.id.fname1);
        lname = (TextView) findViewById(R.id.lname1);
        email = (TextView) findViewById(R.id.email2);
        idnum = (TextView) findViewById(R.id.idnumber);
        acnum = (TextView) findViewById(R.id.accnum);
        address = (TextView) findViewById(R.id.add);
        phone = (TextView) findViewById(R.id.ph);
        balance = (TextView) findViewById(R.id.bls);
        Fill();
        fill2();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Fill();
        fill2();
    }






    public  void Fill ()
    {

        RequestQueue queue = Volley.newRequestQueue(Profile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(Transferinfo.this , response.toString() , Toast.LENGTH_LONG).show();
                            JSONArray object = new JSONArray(response);

                            for(int i = 0 ; i < object.length() ; i++) {
                                JSONObject jsonObject = object.getJSONObject(i);
                                //fname.setText(jsonObject.getString("fname"));
                                //lname.setText(jsonObject.getString("lname"));
                                //email.setText(jsonObject.getString("email"));
                               // idnum.setText(jsonObject.getString("idnum"));
                                acnum.setText(jsonObject.getString("acnum"));
                                //address.setText(jsonObject.getString("address"));
                                //phone.setText(jsonObject.getString("phone"));
                                balance.setText(jsonObject.getString("balance"));
                            }

                        } catch (  Exception e)
                        {
                            Toast.makeText(Profile.this ,e.toString() , Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Profile.this , error.toString() , Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email" ,email2);

                return params;
            }
        };

        queue.add(stringRequest);
    }

    public  void fill2 ()
    {
        RequestQueue queue = Volley.newRequestQueue(Profile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(Transferinfo.this , response.toString() , Toast.LENGTH_LONG).show();
                            JSONArray object = new JSONArray(response);

                            for(int i = 0 ; i < object.length() ; i++) {
                                JSONObject jsonObject = object.getJSONObject(i);
                                fname.setText(jsonObject.getString("fname"));
                                lname.setText(jsonObject.getString("lname"));
                                email.setText(jsonObject.getString("email"));
                                idnum.setText(jsonObject.getString("idnum"));
                                //acnum.setText(jsonObject.getString("acnum"));
                                address.setText(jsonObject.getString("address"));
                                phone.setText(jsonObject.getString("phone"));
                                //balance.setText(jsonObject.getString("balance"));
                            }

                        } catch (  Exception e)
                        {
                            Toast.makeText(Profile.this ,e.toString() , Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Profile.this , error.toString() , Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email" ,email2);

                return params;
            }
        };

        queue.add(stringRequest);
    }
    public void Editinfo (View view)
    {
        if(view.getId() == R.id.editinfo)
        {
            Intent i = new Intent(Profile.this ,  Editprofile.class);
            i.putExtra("idnum" , idnum.getText().toString());
            i.putExtra("email" , email2);
            startActivity(i);
        }

    }


}
