package com.parkitalia.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parkitalia.android.R;

public class SplashActivity extends AppCompatActivity
{
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


            //...........................go to next activity............................//
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();


    }
}
