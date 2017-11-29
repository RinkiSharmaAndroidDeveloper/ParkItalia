package com.parkitalia.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.parkitalia.android.R;

public class MainActivity extends Activity
{
    ImageView signIn, signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SessionManager sessionManager=new SessionManager(this);
        sessionManager.checkLogin();
        //......................initialisation......................//

        signIn = (ImageView) findViewById(R.id.SignIn);
        signUp = (ImageView) findViewById(R.id.SignUp);
        signUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                intent.putExtra("sign_up", "1");
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}
