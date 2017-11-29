package com.parkitalia.android.activities;

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
import com.parkitalia.android.adaptors.ItalianAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pc on 6/22/2017.
 */

public class ItalianRoadApi extends BaseActivity {
    ArrayList<ItalianRoadModel> listModel = new ArrayList<ItalianRoadModel>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        if (listModel != null) {
            listModel.clear();
        }
        String login_url2 = "http://indotesting.com/parkme/webservices/get_rules/get_regulation";

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
                                String title = jsonObject1.getString("title");
                                String image = jsonObject1.getString("image");
                                listModel.add(new ItalianRoadModel(id, title, image));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        ItalianAdapter adapter=new ItalianAdapter(ItalianRoadApi.this,listModel);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ItalianRoadApi.this);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setAdapter(adapter);


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
