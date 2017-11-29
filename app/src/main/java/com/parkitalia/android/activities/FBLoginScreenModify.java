package com.parkitalia.android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parkitalia.android.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Indosurplus on 5/16/2017.
 */

public class FBLoginScreenModify extends Activity {
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.fbscreen);

        LoginManager.getInstance().logOut();

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(FBLoginScreenModify.this, Arrays.asList("public_profile", "user_friends"));
        loginButton= (LoginButton) findViewById(R.id.fblogin_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginManager.getInstance().logInWithReadPermissions(FBLoginScreenModify.this, Arrays.asList("email", "user_friends", "public_profile"));

                Log.e("LoginResullt", "success");
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    String facebook_id = profile.getId();
                    String    f_name = profile.getFirstName();
                    String m_name = profile.getMiddleName();
                    String l_name = profile.getLastName();
                    String full_name = profile.getName();
                    String profile_image = profile.getProfilePictureUri(400, 400).toString();
                    Log.e("DataFB", facebook_id + f_name + m_name + l_name);
                }
                RequestData();
                GraphRequest.newMeRequest(
                        loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject me, GraphResponse response) {
                                if (response.getError() != null) {
                                    // handle error
                                } else {
                                    try {
                                        String email = response.getJSONObject().get("email").toString();
                                        Log.e("Result", email);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    String id = me.optString("id");
                                    // send email and id to your web server
                                    Log.e("Result1", response.getRawResponse());
                                    Log.e("Result", me.toString());
                                }
                            }
                        }).executeAsync();
                // App code
            }

            @Override
            public void onCancel() {
                // App code
                Log.e("LoginResullt", "onCancel");
                Intent intent = new Intent(FBLoginScreenModify.this, MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Intent intent = new Intent(FBLoginScreenModify.this, MainActivity.class);
                startActivity(intent);
                Log.e("LoginResullt", "exception" + exception);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    public void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                Log.e("DataFacebook", String.valueOf(json));

                try {
                    if (json != null) {
                        String emailFb = json.getString("email");
                        String name = json.getString("name");
                        String id = json.getString("id");
                        Log.e("DataFacebook", String.valueOf(json.getString("email") + json.getString("name") + json.getString("id")));
                        ApifbMethod(id, emailFb, name);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void ApifbMethod(String fbid, String emailid, String fname) {
        //    //?fb_id=&email=&first_name=

        RequestQueue queue = Volley.newRequestQueue(FBLoginScreenModify.this);
//        final String url = loginApifb + "?fb_id=" + fbid + "&email=" + emailid + "&name=" + f_name;

    }
}
