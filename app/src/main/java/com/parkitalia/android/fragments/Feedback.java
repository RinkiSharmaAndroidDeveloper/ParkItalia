package com.parkitalia.android.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parkitalia.android.R;
import com.parkitalia.android.activities.BaseActivity;
import com.parkitalia.android.activities.SessionManager;
import com.parkitalia.android.extra.App;
import com.parkitalia.android.extra.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Feedback extends BaseActivity
{
    EditText message;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        onView();
    }

    public void onView()
    {
        message = (EditText) findViewById(R.id.feedback_message);

        findViewById(R.id.Send_Feedback).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)

            {

                    SessionManager sessionManager=new SessionManager(Feedback.this);
                    HashMap<String,String>map=sessionManager.getUserDetails();
                    String id=map.get(SessionManager.KEY_ID);
                    String link="http://indotesting.com/parkme/webservices/feedback/add_feedback?user_id="+id+"&message="+message;
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto","stayincortona@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                }



        });
    }

    private void displayTerms(String url)
    {
        Feedback.this.showProgressing(Feedback.this, String.valueOf(getText(R.string.Loader)));
        RequestQueue queue = Volley.newRequestQueue(Feedback.this);
        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Feedback.this.hideProgressing();
                        try
                        {
                            JSONObject json = new JSONObject(response);
                            Log.e("seema", "ghhhh" + json);

                            if (json.getString("success").equals("1"))
                            {
                                finish();
                                Util.Toast(Feedback.this, "Feedback send successfully.");
                            }
                            else
                            {
                                Util.Toast(Feedback.this, json.getString("success"));
                                Feedback.this.hideProgressing();
                            }

                        }
                        catch (JSONException e1)
                        {
                            Util.Toast(Feedback.this, "Internal server error");
                            e1.printStackTrace();
                            Feedback.this.hideProgressing();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Feedback.this.hideProgressing();
                        Util.Toast(Feedback.this, "Internal server error");
                    }
                }
        );
        queue.add(jsObjRequest);
    }
}
