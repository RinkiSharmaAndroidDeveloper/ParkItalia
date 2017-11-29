package com.parkitalia.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
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

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class SigninActivity extends BaseActivity {
    CallbackManager callbackManager;
    private static final int REQUESTFINELOCAION = 1111;
    private int REQUEST_ID_MULTIPLE_PERMISSIONS = 222;
    EditText user_name, user_pass;
    TextView User_forget_pass, User_sign_up_page;
    String user_id;
    ImageView fb_login;
    CallbackManager callbackmanager;
    LoginManager mLoginManager;
    String lnamefb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     /*   printKeyHash();*/

      /*  try {
            PackageInfo info = getPackageManager().getPackageInfo("com.parkitalia.android", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash:", e.toString());
        }*/

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.signin);
        ImageView imageViewback= (ImageView) findViewById(R.id.back_signin);

        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SigninActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        SessionManager sessionManager = new SessionManager(SigninActivity.this);
        sessionManager.checkLogin();
        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.parkitalia.android",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/


        //.............initilalisation.....................//
        init();


    }
  /*  private void printKeyHash() {
        // Add code to print out the key hash

    }
*/
    private void init() {
        //.............set the id of objects.............//
        user_name = (EditText) findViewById(R.id.signin_email);
        user_pass = (EditText) findViewById(R.id.signin_pass);

        User_forget_pass = (TextView) findViewById(R.id.signin_forget);
        User_sign_up_page = (TextView) findViewById(R.id.sign_up_textview);

        final ImageView backView = (ImageView) findViewById(R.id.back_signin);
        final ImageView login = (ImageView) findViewById(R.id.login_but);
        fb_login = (ImageView) findViewById(R.id.facebook_login);
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
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

                                    String urll = App.FACEBOOK_URL + "fb_id=" + fb_id + "&email=" + email + "&name=" + t;

                                    Log.e("urll", "==" + urll + "///");
                                    Log.e("DataFb32323", "==" + object.toString() + "///" + response.toString());
                                    loginapp(urll);

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
        //.................click action of objects.........................//
//        backView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

        User_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SigninActivity.this, ForgotPasswordScreen.class);
                startActivity(intent);
            }
        });

        User_sign_up_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.putExtra("sign_up", "2");
                startActivity(intent);
                finish();
            }
        });

        fb_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.performClick();

// Intent intent=new Intent(SigninActivity.this,FBLoginScreenModify.class);
//            startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login_username, login_userpass;
                login_username = user_name.getText().toString();
                login_userpass = user_pass.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (login_username.equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "you did not enter email", Toast.LENGTH_SHORT).show();

                } else {

                    //............................hide the keyboard...............................//

                    try {
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                    String url = App.BASE_URL + App.SIGN_IN + "email=" + login_username + "&password=" + login_userpass;
                    Log.e("seema", "url" + url);
                        loginapp23(url);
                }

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //...................set fblogin...................//
    private void onFblogin() {
        callbackmanager = CallbackManager.Factory.create();

        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_photos", "email", "public_profile", " user_birthday"));

        LoginManager.getInstance().registerCallback(callbackmanager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        System.out.println("Success");
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {

                                        Log.e("response", "response" + object);

                                        if (response != null) {
                                            try {

                                                String birthday = "";
                                                String email = "";
                                                JSONObject graph = response.getJSONObject();
                                                email = graph.getString("email");

                                                if (graph.has("birthday")) {

                                                    birthday = graph.getString("birthday");
                                                } else {
                                                }
                                                String image = graph.getJSONObject("picture").getJSONObject("data").getString("url");
                                                Log.e("image", "image" + image);
                                                String fb_id = graph.getString("id");
                                                String gender = graph.getString("gender");

                                                String name = graph.getString("name");
                                                String fb_flag = "1";
                                                String password = "12345";
                                                String f_name = graph.getString("first_name");
                                                lnamefb = graph.getString("last_name");
                                                Log.e("seeema", "seema" + image + name + birthday + password + gender + fb_id);

                                                String urll = App.FACEBOOK_URL + "fb_id=" + fb_id + "&email=" + fb_id + "@parktaliagmail.com" + "&name=" + f_name;
                                                Log.e("sim", "url" + urll);

                                                loginapp2(urll);

                                            } catch (Exception e) {
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
                    public void onCancel() {
                        Log.d("FbLoginActivty", "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("FbLoginActivty", error.toString());
                    }

                });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackmanager.onActivityResult(requestCode, resultCode, data);
//        mLoginManager = LoginManager.getInstance();
//        mLoginManager.logOut();
//    }
public void loginapp(String login_url2) {
    showProgressing(SigninActivity.this, String.valueOf(getText(R.string.Loader)));
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
                                Toast.makeText(SigninActivity.this, "Login Successful",
                                        Toast.LENGTH_LONG).show();
                                user_id = response.getString("id");
                                String fname=response.getString("first_name");
                                // String lname=jsonobject.getString("last_name");

                                String emailid=response.getString("email");
                                String lname="";
                                //  Log.e("Data Enter", user_id + "///" + fname + "///" + lname + "///" + emailid);

                                SessionManager sessionManager=new SessionManager(SigninActivity.this);
                                sessionManager.createLoginSession(user_id, emailid, fname, lname);

                                //...................go to next activity..............//
                                Intent intent = new Intent(SigninActivity.this, LandingScreen.class);
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
/*
    private void loginapp(String login_url2)
    {
        // TODO Auto-generated method stub
        showProgressing(SigninActivity.this, String.valueOf(getText(R.string.Loader)));

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, login_url2,
                new Response.Listener<JSONObject>()
                {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        // TODO Auto-generated method stub
                        Log.d("SigninActivity", "response" + response);
                        try
                        {
                            ;
                           String sucess= response.getString("success");
                          //  Log.e("JsonObject",jsonobject.toString());
                            if (sucess.equals("1"))
                            {
                                //.method.equals("login")...................show toast.............................//
                                //  Util.Toast(SigninActivity.this, jsonobject"Login Successful");
                                Toast.makeText(SigninActivity.this, "Login Successful",
                                        Toast.LENGTH_LONG).show();
                                user_id = response.getString("id");
                                String fname=response.getString("first_name");
                               // String lname=jsonobject.getString("last_name");

                                String emailid=response.getString("email");
                                String lname="";
                                //  Log.e("Data Enter", user_id + "///" + fname + "///" + lname + "///" + emailid);

                                SessionManager sessionManager=new SessionManager(SigninActivity.this);
                                sessionManager.createLoginSession(user_id, emailid, fname, lname);

                                //...................go to next activity..............//
                                Intent intent = new Intent(SigninActivity.this, LandingScreen.class);
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

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                // TODO Auto-generated method stub

             *//*   NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }*//*

            }
        }
        );

        queue.add(jsObjRequest);
    }*/

    private void loginapp1(String login_url2)
    {
        // TODO Auto-generated method stub
        showProgressing(SigninActivity.this, String.valueOf(getText(R.string.Loader)));

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url2,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response)
                    {
                        // TODO Auto-generated method stub
                        Log.d("SigninActivity", "response" + response);
                        try
                        {
                            JSONObject json = new JSONObject(response);
                            JSONObject jsonobject = json.getJSONObject("response");
                            Log.e("JsonObject",jsonobject.toString());
                            if (json.getString("status").equalsIgnoreCase("success"))
                            {
                                //.method.equals("login")...................show toast.............................//
                                //  Util.Toast(SigninActivity.this, jsonobject"Login Successful");
                                Toast.makeText(SigninActivity.this, "Login Successful",
                                        Toast.LENGTH_LONG).show();
                                user_id = jsonobject.getString("id");
                                String fname=jsonobject.getString("first_name");
                                String lname=jsonobject.getString("last_name");
                                String emailid=jsonobject.getString("email");
                                Log.e("Data Enter", user_id + "///" + fname + "///" + lname + "///" + emailid);

                                SessionManager sessionManager=new SessionManager(SigninActivity.this);
                                sessionManager.createLoginSession(user_id, emailid, fname, lname);

                                //...................go to next activity..............//
                                Intent intent = new Intent(SigninActivity.this, LandingScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                hideProgressing();
                            }
                            else
                            {
                                Util.Toast(SigninActivity.this, jsonobject.getString("resultText"));
                                hideProgressing();
                            }

                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener()
        {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                // TODO Auto-generated method stub

                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }

            }
        }
        );

        queue.add(jsObjRequest);
    }
    /*
    private void loginapp(String login_url2) {
        // TODO Auto-generated method stub
        showProgressing(SigninActivity.this, String.valueOf(getText(R.string.Loader)));

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

                            SessionManager sessionManager = new SessionManager(SigninActivity.this);
                            sessionManager.createLoginSession(user_id, emailid, fname, lnamefb);

                            //...................go to next activity..............//
                            Intent intent = new Intent(SigninActivity.this, LandingScreen.class);
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
    }
*/
    private void loginapp23(String login_url2) {
        // TODO Auto-generated method stub
        showProgressing(SigninActivity.this, String.valueOf(getText(R.string.Loader)));

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url2,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Log.d("SigninActivity", "response" + response);
                        try {
                            JSONObject jsonObjectresponse = new JSONObject(response);
                            Log.e("StatusFail12", jsonObjectresponse.toString());

                            jsonObjectresponse.getString("response");
                            String status = jsonObjectresponse.getString("status");
                            Log.e("StatusFail", status);
                            if (status.equals("failure")) {
                                hideProgressing();
                                String responsefail = jsonObjectresponse.getString("response");
                                JSONObject jsonObject = new JSONObject(responsefail);
                                jsonObject.getString("resultText");
                                Log.e("resultText", jsonObject.getString("resultText"));
                                Toast.makeText(SigninActivity.this,jsonObject.getString("resultText"),Toast.LENGTH_SHORT).show();


                            }
                            JSONObject json = new JSONObject(jsonObjectresponse.getString("response"));


                            Toast.makeText(SigninActivity.this, "Login Successful",
                                    Toast.LENGTH_LONG).show();

                            user_id = json.getString("id");
                            String fname = json.getString("first_name");
                            String emailid = json.getString("email");
                            Log.e("DataEnter", user_id + "///" + fname + "///" + lnamefb + "///" + emailid);

                            SessionManager sessionManager = new SessionManager(SigninActivity.this);
                            sessionManager.createLoginSession(user_id, emailid, fname, lnamefb);

                            //...................go to next activity..............//
                            Intent intent = new Intent(SigninActivity.this, LandingScreen.class);
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
    }

    private void loginapp2(String login_url) {
        // TODO Auto-generated method stub
        showProgressing(SigninActivity.this, String.valueOf(getText(R.string.Loader)));

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Log.e("gggggg", "response" + response);
                        try {
                            JSONObject json = new JSONObject(response);

                            Log.e("seema", "ghhhh" + json);

                            if (json.getString("success").equals("1")) {
                                Log.e("seema", "ghhhh");
                                user_id = json.getString("id");

                                //...................go to next activity..............//
                                Intent intent = new Intent(SigninActivity.this, LandingScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                hideProgressing();
                            } else {
                                Util.Toast(SigninActivity.this, json.getString("success"));

                                hideProgressing();
                            }

                        } catch (JSONException e1) {
                            Util.Toast(SigninActivity.this, "Internal server error");
                            e1.printStackTrace();
                            hideProgressing();
                        }

//                                User_type = json.getString(Prefrences.SIGNIN_USER_TYPE);
//                                latitude = json.getString(Prefrences.SIGNIN_LAT);
//                                longitude = json.getString(Prefrences.SIGNIN_LONG);
//                                Usre_id = json.getString(Prefrences.SIGNING_USER_ID);
//                                InfoStatus = json.getString(Prefrences.SIGNIN_INFO_STATUS);
//                                Gender = json.getString(Prefrences.SIGNIN_GENDER);

//                                SharedPreferences Shared = getSharedPreferences(Myprefs, Context.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = Shared.edit();
//                                editor.putString("User_id", Usre_id);
//                                editor.commit();

//                                Prefrences.setSigninUserId(getApplicationContext(),Usre_id);

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
                hideProgressing();
                Util.Toast(SigninActivity.this, "Internal server error");

            }
        }
        );

        queue.add(jsObjRequest);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent=new Intent(SigninActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return false;
    }
}

    /*private void loginapp(String login_url2)
    {
        // TODO Auto-generated method stub
        showProgressing(SigninActivity.this, String.valueOf(getText(R.string.Loader)));

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url2,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response)
                    {
                        // TODO Auto-generated method stub
                        Log.d("SigninActivity", "response" + response);
                        try
                        {
                            JSONObject json = new JSONObject(response);

                            JSONObject jsonobject = json.getJSONObject("response");
                            Log.e("JsonObject",jsonobject.toString());
                            if (json.getString("status").equalsIgnoreCase("success"))
                            {
                                //.method.equals("login")...................show toast.............................//
                                //  Util.Toast(SigninActivity.this, jsonobject"Login Successful");
                                Toast.makeText(SigninActivity.this, "Login Successful",
                                        Toast.LENGTH_LONG).show();

                                user_id = jsonobject.getString("id");
                                String fname=jsonobject.getString("first_name");
                                String lname=jsonobject.getString("last_name");
                                String emailid=jsonobject.getString("email");
                                Log.e("Data Enter",user_id+"///"+fname+"///"+lname+"///"+emailid);

                                SessionManager sessionManager=new SessionManager(SigninActivity.this);
                                sessionManager.createLoginSession(user_id,emailid,fname,lname);

                                //...................go to next activity..............//
                                Intent intent = new Intent(SigninActivity.this, LandingScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                                hideProgressing();
                            }
                            else
                            {
                                Util.Toast(SigninActivity.this, jsonobject.getString("resultText"));
                                hideProgressing();
                            }

                        }
                        catch (Exception e)
                        {
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

*/