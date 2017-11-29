package com.parkitalia.android.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parkitalia.android.R;
import com.parkitalia.android.extra.App;
import com.parkitalia.android.extra.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Indosurplus on 3/30/2017.
 */

public class SignUpActivity extends BaseActivity
{
    EditText User_fname, User_lname, User_email, User_pass, User_confrmpass;
    String user_id, sign_up_id;
    ImageView fb_login;
    ProgressDialog mProgressDialog;
    CallbackManager callbackManager;
    String lnamefb;

    CallbackManager callbackmanager;
    LoginManager mLoginManager;
     String urll2;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.signup);
        mProgressDialog = new ProgressDialog(SignUpActivity.this);
        mProgressDialog.setIndeterminate(false);

        mProgressDialog.setTitle("Please Wait");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginResult.getAccessToken();

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                Log.e("DataFb32323", "==" + object.toString() + "///" + response.toString());

                                try {


                                    String fname = object.getString("first_name");
                                    String email = object.getString("email");
                                    String fb_id = object.getString("id");
                                    String name = object.getString("name");
                                    String t=name.replaceAll("\\s+","");
                                    urll2 = App.FACEBOOK_URL + "fb_id=" + fb_id + "&email="+ email + "&name=" + t;
                                    loginapp22(urll2);

                                    Log.e("urll", "==" + urll2 + "///" );
                                    Log.e("DataFb32323", "==" + object.toString() + "///" + response.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                // Application code
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email,first_name,last_name");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        });

        //..............intialisation...........//

        init();

    }

    public void loginapp22(String login_url2) {
        showProgressing(SignUpActivity.this, String.valueOf(getText(R.string.Loader)));
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, login_url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        Log.e("Response", response.toString());

                        try
                        {
                            ;
                            String sucess= response.getString("success");
                            //  Log.e("JsonObject",jsonobject.toString());
                            if (sucess.equals("1"))
                            {
                                //.method.equals("login")...................show toast.............................//
                                //  Util.Toast(SigninActivity.this, jsonobject"Login Successful");
                                Toast.makeText(SignUpActivity.this, "Signup Successful",
                                        Toast.LENGTH_LONG).show();
                                user_id = response.getString("id");
                                String fname=response.getString("first_name");
                                // String lname=jsonobject.getString("last_name");

                                String emailid=response.getString("email");
                                String lname="";
                                //  Log.e("Data Enter", user_id + "///" + fname + "///" + lname + "///" + emailid);

                                SessionManager sessionManager=new SessionManager(SignUpActivity.this);
                                sessionManager.createLoginSession(user_id, emailid, fname, lname);

                                //...................go to next activity..............//
                                Intent intent = new Intent(SignUpActivity.this, LandingScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                hideProgressing();
                            }
                            else
                            {
                                //  Util.Toast(SigninActivity.this, jsonobject.getString("resultText"));
                                hideProgressing();
                            }

                        } catch (JSONException e) {

                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(jsObjRequest);
    }
    /*public void loginapp22(String login_url2) {
        // TODO Auto-generated method stub
        showProgressing(SignUpActivity.this, String.valueOf(getText(R.string.Loader)));

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url2,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Log.d("SigninActivity", "response" + response);
                        try {
                            JSONObject json = new JSONObject(response);
                            user_id = json.getString("id");
                            String fname = json.getString("first_name");
                            String emailid = json.getString("email");
                            Log.e("DataEnter", user_id + "///" + fname + "///" + lnamefb + "///" + emailid);
                            SessionManager sessionManager = new SessionManager(SignUpActivity.this);
                            sessionManager.createLoginSession(user_id, emailid, fname, lnamefb);
                            //...................go to next activity..............//
                            Intent intent = new Intent(SignUpActivity.this, LandingScreen.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                            hideProgressing();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        }
        );

        queue.add(jsObjRequest);
    }*/

    private void init()
    {
        //..................get intent values............//

        sign_up_id = getIntent().getStringExtra("sign_up");

        //.....................set the objects id..................//
        User_fname = (EditText) findViewById(R.id.firstname_edittext);
        User_lname = (EditText) findViewById(R.id.lastname_edittext);
        User_email = (EditText) findViewById(R.id.email_edittext);
        User_pass = (EditText) findViewById(R.id.pass_edittext);
        User_confrmpass = (EditText) findViewById(R.id.confrm_edittext);

        Button signup = (Button) findViewById(R.id.signup);
        ImageView backview = (ImageView) findViewById(R.id.back_signup);
        fb_login = (ImageView) findViewById(R.id.sign_up_fb);

        //..............click action on objects.................//
        backview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sign_up_id.equals("1"))
                {
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        fb_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //...............action on buttons......................//

                loginButton.performClick();
            }
        });

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String fuser_name, luser_name, user_email, user_pass, user_confirmpass;
                fuser_name = User_fname.getText().toString();
                luser_name = User_lname.getText().toString();
                user_email = User_email.getText().toString();
                user_pass = User_pass.getText().toString();
                user_confirmpass = User_confrmpass.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (fuser_name.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "you did not enter first name", Toast.LENGTH_SHORT).show();

                }
                else if (luser_name.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "you did not enter last name", Toast.LENGTH_SHORT).show();

                }
                else if (user_email.equalsIgnoreCase(""))
                {
                    Toast.makeText(getApplicationContext(), "you did not enter email, plz enter email", Toast.LENGTH_SHORT).show();

                }
                else if (!user_email.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(), "Invalid email , plz check email", Toast.LENGTH_SHORT).show();

                }
                else if (user_pass.equals("") && user_pass.length() <= 6)
                {
                    Toast.makeText(getApplicationContext(), "plz check password", Toast.LENGTH_SHORT).show();

                }
                else if (user_confirmpass.equals("") && user_confirmpass.length() <= 6)
                {
                    Toast.makeText(getApplicationContext(), "plz check confirm password", Toast.LENGTH_SHORT).show();

                }
                else if (!user_confirmpass.matches(user_pass))
                {
                    Toast.makeText(getApplicationContext(), "confirm password did not match with password ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    mProgressDialog.show();


                    String url = App.BASE_URL + App.SIGN_UP +"first_name="+fuser_name+"&last_name="+luser_name+"&email="+user_email+"&password="+user_pass+"&retype_password="+user_confirmpass;
                    Log.e("url", "url" + url);
                    loginapp(url,user_email,fuser_name,luser_name);
                }

            }
        });

    }

    private void loginapp(String login_url2, final String user_email, final String fname, final String lname)
    {
        // TODO Auto-generated method stub

        RequestQueue queue = Volley.newRequestQueue(this);

        final StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url2,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response)
                    {
                        // TODO Auto-generated method stub
                        //05-19 17:21:05.094 24882-24882/com.parkitalia.android E/Response:
                        // {"user_id":"174","resultText":"Register successfully"}

                        Log.d("Signupdata", "response" + response);
                        try
                        {
                            JSONObject json = new JSONObject(response);
                            String responseresult=json.getString("status");
                            if(responseresult.equals("success")){
                                String responseData=json.getString("response");
                                JSONObject jsonObject=new JSONObject(responseData);
                                String uid= jsonObject.getString("user_id");
                                Log.e("UID",uid);
                                SessionManager sessionManager=new SessionManager(SignUpActivity.this);
                                sessionManager.createLoginSession(uid,user_email,fname,lname);
                                mProgressDialog.dismiss();
                                Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }

//                            if (json.getString("status").equalsIgnoreCase("success"))
//                            {
//
//                                JSONObject jsonobject = json.getJSONObject("response");
//
//                                Log.e("Response",jsonobject.toString());
//                                //....................show toast.............................//
////                                Util.Toast(SignUpActivity.this, jsonobject.getString("resultText"));
//
//                                user_id = jsonobject.getString("id");
//
//                                //...................go to next activity..............//
//
//                                Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                startActivity(intent);
//                                finish();
//                            }
                            else
                            {
                                mProgressDialog.dismiss();

                                Toast.makeText(getApplicationContext(), "Email id exist, please try another email", Toast.LENGTH_SHORT).show();

                            }

                        }
                        catch (Exception e)
                        {
                            mProgressDialog.dismiss();

                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                // TODO Auto-generated method stub

            }
        }
        );

        queue.add(jsObjRequest);
    }

    private void loginapp2(String login_url)
    {
        // TODO Auto-generated method stub.

        showProgressing(SignUpActivity.this, String.valueOf(getText(R.string.Loader)));

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response)
                    {
                        // TODO Auto-generated method stub
                        Log.e("gggggg", "response" + response);
                        try
                        {
                            JSONObject json = new JSONObject(response);

                            Log.e("seema", "ghhhh" + json);

                            if (json.getString("success").equals("1"))
                            {
                                Log.e("seema", "ghhhh");
                                user_id = json.getString("id");

                                //...................go to next activity..............//
                                Intent intent = new Intent(SignUpActivity.this, LandingScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                hideProgressing();
                            }
                            else
                            {
                                Util.Toast(SignUpActivity.this, json.getString("success"));
                                hideProgressing();
                            }

                        }
                        catch (JSONException e1)
                        {
                            e1.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                // TODO Auto-generated method stub

            }
        }
        );

        queue.add(jsObjRequest);
    }

    //...................set fblogin...................//
    private void onFblogin()
    {
        callbackmanager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", " user_birthday"));

        LoginManager.getInstance().registerCallback(callbackmanager,
                new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {

                        System.out.println("Success");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback()
                                {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response)
                                    {

                                        Log.e("response", "response" + object);

                                        if (response != null)
                                        {
                                            try
                                            {

                                                String birthday = "";
                                                String email = "";
                                                JSONObject graph = response.getJSONObject();
                                                email = graph.getString("email");

                                                if (graph.has("birthday"))
                                                {

                                                    birthday = graph.getString("birthday");
                                                }
                                                else
                                                {
                                                }
                                                String image = graph.getJSONObject("picture").getJSONObject("data").getString("url");
                                                Log.e("image", "image" + image);
                                                String fb_id = graph.getString("id");
                                                String gender = graph.getString("gender");

                                                String name = graph.getString("name");
                                                String fb_flag = "1";
                                                String password = "12345";
                                                String f_name = graph.getString("first_name");

                                                Log.e("seeema", "seema" + image + name + birthday + password + gender + fb_id);

                                                String urll = App.FACEBOOK_URL + "fb_id=" + fb_id + "&email=" +fb_id+ "@parktaliagmail.com" + "&name=" + f_name;
                                                Log.e("sim", "url" + urll);

                                                loginapp2(urll);

                                            }
                                            catch (Exception e)
                                            {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
//                                        Log.v("LoginActivity", response.toString());
//                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "first_name,id,name,email, birthday,gender,picture");
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel()
                    {
                        Log.d("FbLoginActivty", "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error)
                    {
                        Log.d("FbLoginActivty", error.toString());
                    }

                });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        if (sign_up_id.equals("1"))
        {
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
