package com.parkitalia.android.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.parkitalia.android.R;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Indosurplus on 5/24/2017.
 */

public class ForgotPasswordScreen extends Activity {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        pattern = Pattern.compile(EMAIL_PATTERN);
        ImageView imageView= (ImageView) findViewById(R.id.back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordScreen.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        final EditText editText = (EditText) findViewById(R.id.edittemail);
        Button button = (Button) findViewById(R.id.sumbit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate(editText.getText().toString()) == true) {
                    API(editText.getText().toString());
                } else {
                    Toast.makeText(ForgotPasswordScreen.this, "Please Enter Valid Mail.", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

    public void API(String data) {
        final ProgressDialog progressDialog = new ProgressDialog(ForgotPasswordScreen.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
//        http://vertosys.com/docpat/ForgotPassword.php?email=
        String link = "http://indotesting.com/parkme/webservices/user/forgot_password?email=" + data;
        final RequestQueue queue = Volley.newRequestQueue(ForgotPasswordScreen.this);

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, link, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("datares",response.toString());
                    String sucess = response.getString("status");
                    if (sucess.equals("failure")) {
                        progressDialog.dismiss();

                        Toast.makeText(ForgotPasswordScreen.this, "Email ID not Exist !", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();

                        Toast.makeText(ForgotPasswordScreen.this, " Password has been sent to your registered Email ID!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ForgotPasswordScreen.this, SigninActivity.class);
                        startActivity(intent);
                    }


                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(ForgotPasswordScreen.this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();


                }


                // TODO Auto-generated method stub

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub


            }
        });

        queue.add(jsObjRequest);
    }
}
