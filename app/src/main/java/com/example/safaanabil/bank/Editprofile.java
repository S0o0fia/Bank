package com.example.safaanabil.bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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

public class Editprofile extends AppCompatActivity {

    String idnum ;
    EditText email , address , phone ;
    final String url1  = "http://192.168.137.1/android/profile2.php";
    final String url2  = "http://192.168.137.1/android/edit.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        idnum = getIntent().getStringExtra("idnum");

        email = (EditText) findViewById(R.id.email3);
        address = (EditText) findViewById(R.id.add2);
        phone = (EditText) findViewById(R.id.ph2);
        RequestQueue queue = Volley.newRequestQueue(Editprofile.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Toast.makeText(Transferinfo.this , response.toString() , Toast.LENGTH_LONG).show();
                            JSONArray object = new JSONArray(response);

                            for(int i = 0 ; i < object.length() ; i++) {
                                JSONObject jsonObject = object.getJSONObject(i);
                                email.setText(jsonObject.getString("email"));
                                address.setText(jsonObject.getString("address"));
                                phone.setText(jsonObject.getString("phone"));
                            }

                        } catch (  Exception e)
                        {
                            Toast.makeText(Editprofile.this ,e.toString() , Toast.LENGTH_LONG).show();

                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Editprofile.this , error.toString() , Toast.LENGTH_LONG).show();

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



    public void pass (View view)
    {

        if(view.getId() == R.id.passinfo)
        {
            Intent i = new Intent(Editprofile.this ,  Changepass.class);
            i.putExtra("idnum" , idnum);
            i.putExtra("email" , email.getText().toString());
            startActivity(i);
        }
    }



    public  void Save (View view)
    {
        if(view.getId() == R.id.save)
        {
            RequestQueue queue = Volley.newRequestQueue(Editprofile.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new
                    Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        Intent i = new Intent(Editprofile.this , Profile.class);
                        i.putExtra("email" , email.getText().toString());
                        startActivity(i);


                                                   }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Editprofile.this , error.toString() , Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email" , email.getText().toString());
                    params.put("phone" , phone.getText().toString());
                    params.put("address" , address.getText().toString());
                    params.put("idnum" , idnum );
                    return params;
                }
            };

            queue.add(stringRequest);

        }
    }


    public  void cancel(View view)
    {
        if(view.getId() == R.id.cancel)
        {
            Intent i = new Intent(Editprofile.this , Profile.class);
            i.putExtra("email" , email.getText().toString());
            startActivity(i);

        }
    }
}
