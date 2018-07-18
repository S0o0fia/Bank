package com.example.safaanabil.bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.MapFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText email , password ;
    final String url="http://192.168.137.1/android/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
    }


    //when clicking login button
    public void Login (View view)
    {

        if(view.getId() == R.id.login)
        {
            if(email.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
            {
                Intent i = new Intent(MainActivity.this ,MainHome.class);
                i.putExtra("email" , email.getText().toString());
                startActivity(i);
            }

            try {
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      //Log.i("Volley", response);
                        //Toast.makeText(MainActivity.this , response , Toast.LENGTH_LONG).show();
                        if(response.equals("login"))
                        {
                            Intent i = new Intent(MainActivity.this , MainHome.class);
                            i.putExtra("email" , email.getText().toString());
                            startActivity(i);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(MainActivity.this , error.toString() , Toast.LENGTH_LONG).show();

                        //Log.e("Volley" , error.toString());
                    }
                }

                ){
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                         params.put("email" , email.getText().toString());
                        params.put("password" , password.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            } catch ( Exception e)
            {
                e.printStackTrace();
            }



        }
        }
   //when clicking sign up button
    public void sginup (View view)
    {

        if(view.getId() == R.id.signup)
        {
            Intent intent = new Intent(MainActivity.this , Register.class);
            startActivity(intent);
        }
    }


}
