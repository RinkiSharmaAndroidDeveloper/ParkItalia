package com.parkitalia.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parkitalia.android.R;
import com.parkitalia.android.extra.Util;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Indosurplus on 5/22/2017.
 */

public class MyAccount extends Activity {
    ProgressDialog progressDialog;
    SessionManager sessionManager;

    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        sessionManager=new SessionManager(this);
        ImageView imageView= (ImageView) findViewById(R.id.back_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this, LandingScreen.class);
                intent.putExtra("Key_Data", "0");
                intent.putExtra("Key_Data1", "1");

                startActivity(intent);

            }
        });
        HashMap<String, String> user = sessionManager.getUserDetails();
        String email= user.get(SessionManager.KEY_EMAIL);
        link="http://indotesting.com/parkme/webservices/user/get_userid?email="+email;
        //http://indotesting.com/parkme/webservices/user/get_userid?email=ramnish@gmail.com
        api();
    }
    public void api(){
        progressDialog=new ProgressDialog(MyAccount.this);
        progressDialog.setTitle("Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        try {
                            // TODO Auto-generated method stub
                            Log.d("SigninActivity", "response" + response);
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if(status.equals("success")){
                               String responsecomes= jsonObject.getString("response");
                                JSONObject jsonObject1=new JSONObject(responsecomes);
                                String fname= jsonObject1.getString("first_name");
                                String lname= jsonObject1.getString("last_name");
                                String email=jsonObject1.getString("email");
                                String images= jsonObject1.getString("image");
                                ImageView imageView= (ImageView) findViewById(R.id.image_profile);
                                Picasso.with(MyAccount.this).load(images).into(imageView);
                                TextView textViewName= (TextView) findViewById(R.id.nameprofile);
                                textViewName.setText(fname+" "+lname);
                                TextView textViewemail= (TextView) findViewById(R.id.email_profile);
                                textViewemail.setText(email
                                );

                                progressDialog.dismiss();


                            }
                            else {
                                progressDialog.dismiss();

                            }
                        }catch (Exception e){
                            progressDialog.dismiss();

                        }
                    }
                }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                // TODO Auto-generated method stub
                progressDialog.dismiss();

            }
        }
        );

        queue.add(jsObjRequest);
    }
}
