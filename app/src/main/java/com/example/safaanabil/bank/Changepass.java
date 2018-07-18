package com.example.safaanabil.bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.util.HashMap;
import java.util.Map;

public class Changepass extends AppCompatActivity {

    EditText oldpass , newpass;
    final String url1  = "http://192.168.137.1/android/pass.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);


        oldpass = (EditText) findViewById(R.id.oldpass);
        newpass = (EditText) findViewById(R.id.newpass);

    }

    public  void savepass (View view)
    {
        if(view.getId() == R.id.savepass)
        {
            RequestQueue queue = Volley.newRequestQueue(Changepass.this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new
                    Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Changepass.this , response.toString() , Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Changepass.this , Profile.class);
                            i.putExtra("email" , getIntent().getStringExtra("email"));
                            startActivity(i);

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(Changepass.this , error.toString() , Toast.LENGTH_LONG).show();

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("idnum" , getIntent().getStringExtra("idnum"));
                    params.put("newpass" , newpass.getText().toString());

                    return params;
                }
            };

            queue.add(stringRequest);

        }

    }



    public  void cancelpass (View view)
    {


        if(view.getId() == R.id.cancelpass)
        {


            Intent i = new Intent(Changepass.this , Profile.class);
            i.putExtra("email" , getIntent().getStringExtra("email"));
            startActivity(i);
        }
    }
}
