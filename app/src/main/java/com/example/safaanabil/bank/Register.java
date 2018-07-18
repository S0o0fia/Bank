package com.example.safaanabil.bank;

import android.app.AlertDialog;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    //ImageView profile;
    //private int GALLERY = 1, CAMERA = 2;
   // private String dir = "/images";

    EditText fname , lname , email , password , idnum , acnum , phone , address , bday ;
    CheckBox cur , save ;
    Button register;
    String type;
    final String url="http://192.168.137.1/android/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //define the layout component
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        idnum = (EditText) findViewById(R.id.idnum);
        acnum = (EditText) findViewById(R.id.acnum);
        phone = (EditText)  findViewById(R.id.num);
        address =(EditText) findViewById(R.id.address);
        bday = (EditText) findViewById(R.id.bdate);
        cur = (CheckBox) findViewById(R.id.cuurent);
        save = (CheckBox) findViewById(R.id.save);
        register = (Button) findViewById(R.id.register);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type ="1";
            }
        });
        cur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "2";
            }
        });
    }


    public void register (View view)
    {
        if(view.getId() == register.getId())
        {
          try {


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      Log.i("Volley", response);


                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      Log.e("Volley" , error.toString());
                  }
              }

              ){
                //adding parameters to the request
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("fname" , fname.getText().toString());
                    params.put("lname" , lname.getText().toString());
                    params.put("email" , email.getText().toString());
                    params.put("password" , password.getText().toString());
                    params.put("bday" , bday.getText().toString());
                    params.put("idnum" , idnum.getText().toString());
                    params.put("address" , address.getText().toString());
                    params.put("phone" ,phone.getText().toString());
                    params.put("acnum" , acnum.getText().toString());
                    params.put("type" , type);
                    return params;
                }
                  };


                 requestQueue.add(stringRequest);


          } catch ( Exception e)
          {
              e.printStackTrace();
          }


            Intent i = new Intent(Register.this , MainHome.class);
            i.putExtra("email" , email.getText().toString());
            startActivity(i);
        }
    }
}







/* profile = (ImageView) findViewById(R.id.img);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeimg(profile);
            }
        });
    }

    public  void changeimg (View view)
    {
           //define the options to capture image
        final CharSequence [] items = { "Choose from Gallery" , "Take Photo" };

        //TextView camoption , galoption ;
        //ImageView camera , gallery ;
        //Button cancel = (Button) findViewById( R.id.cnacel); ;
        //camoption = (TextView) findViewById(R.id.cameraoption);
        //galoption = (TextView) findViewById(R.id.galleryoption);
        //camera = (ImageView) findViewById(R.id.camera);
        //gallery = (ImageView)findViewById(R.id.gallery);

        //camoption.setText(items[0]);
        //camoption.setText(items[1]);
        //cancel.setText(items[2]);

        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
        builder.setTitle("Add Image");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which)
                {
                    case 0 :
                        choosefromgallery();
                    case 1 :
                        choosfromcamera();
                }
            }
        });
       builder.show();



    }

    public void choosefromgallery()
    {
        Intent galleryintent = new Intent(Intent.ACTION_PICK ,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryintent , GALLERY);
    }

    public void choosfromcamera ()
    {
         //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
         //startActivityForResult(intent ,CAMERA);

        android.hardware.Camera c = null;
        try {
            c = android.hardware.Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
    }

    @Override
    public  void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode , resultCode,data);

        if(requestCode == 0)
          return;

        if(requestCode == GALLERY)
        {

            if(data != null)
            {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    profile.setImageBitmap(bitmap);
                    saveImage(bitmap);
                    Toast.makeText(Register.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else if (requestCode == CAMERA) {
            Bitmap  image = (Bitmap) data.getExtras().get("data");
            profile.setImageBitmap(image);
            saveImage(image);
            Toast.makeText(Register.this, "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }


       public void saveImage(Bitmap bitmap)
       {
           ContextWrapper cw = new ContextWrapper(getApplication());
           //path to the photo
           File dirctory = cw.getDir("Bank" , Context.MODE_PRIVATE);
           //create Bank dir
           File myPath = new File(dirctory , "profile.jpg");

           FileOutputStream fos = null ;
           try{
               fos = new FileOutputStream(myPath);
               bitmap.compress(Bitmap.CompressFormat.JPEG , 100 , fos);


           }catch (Exception e)
           {
               e.printStackTrace();
           }
            finally {
               try {
                   fos.close();

               }catch (IOException e)
               {
                   e.printStackTrace();
               }
           }



                  //return dirctory.getAbsolutePath();
}*/







