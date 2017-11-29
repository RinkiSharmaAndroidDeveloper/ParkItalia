package com.parkitalia.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.parkitalia.android.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pc on 6/22/2017.
 */

public class ServiceClass extends Activity {
    RecyclerView recyclerView;
    ArrayList<ServiceModel> listmodel = new ArrayList<ServiceModel>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        if (listmodel != null) {
            listmodel.clear();
        }

        String login_url2 = "http://indotesting.com/parkme/webservices/services/get_services";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, login_url2,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Log.d("SigninActivity", "response" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String details = jsonObject.getString("Details");
                            Log.e("Details", details);
                            JSONArray array = new JSONArray(details);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                String id = jsonObject1.getString("id");
                                String addresss = jsonObject1.getString("address");
                                String lat = jsonObject1.getString("lat");
                                String vlong = jsonObject1.getString("lng");
                                String desc = jsonObject1.getString("desc");
                                String phone = jsonObject1.getString("phone");
                                String title = jsonObject1.getString("title");
                                String image = jsonObject1.getString("image");
                                listmodel.add(new ServiceModel(id, title, addresss, desc,phone,lat,vlong,image));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ServiceAdaapterr adapter=new ServiceAdaapterr(ServiceClass.this,listmodel);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ServiceClass.this);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(adapter);

for(int i=0;i<listmodel.size();i++){
    Log.e("dataModel",listmodel.get(i).getId());
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
}