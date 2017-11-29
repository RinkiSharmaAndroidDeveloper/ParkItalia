package com.parkitalia.android.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.parkitalia.android.R;
import com.parkitalia.android.adaptors.DinningAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vishal mahajan on 7/28/2017.
 */
public class InformationDetailsDinning extends BaseActivity {
    RecyclerView recyclerView;
    DinningAdapter mAdapter;
    DinningModel accomodationModel;
    ArrayList<DinningModel> accomodationModels =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_details_dinning);
        recyclerView= (RecyclerView)findViewById(R.id.recycler_view);
        setAdapter();
        getDoctorHistory();


    }
    public void getDoctorHistory(){
        RequestQueue queue2 = Volley.newRequestQueue(this);
        String url2="http://indotesting.com/parkme/webservices/dining/get_dining";
        Log.e("usere",url2);
        JsonObjectRequest jsObjRequest2 = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>()  {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("Response", response.toString());
                String responsemessage= null;
                try {
                    response.getString("Details");
                    Log.e("Data23", response.getString("Details"));
                    JSONArray jsonObj = new JSONArray(response.getString("Details"));
                    for (int i = 0; i < jsonObj.length(); i++) {
                        JSONObject c = jsonObj.getJSONObject(i);
                        String id = c.getString("id");
                        Log.e("Data23", ""+id);

                        String accomd_name = c.getString("title");
                        String accomd_location = c.getString("address");
                        String accomd_wevsite = c.getString("desc");
                        String accomd_phone = c.getString("phone");
                        String image = c.getString("image");
                        String Accomd_gps_lat = c.getString("lat");
                        String Accomd_gps_lng = c.getString("lng");
                        String accomd_type = c.getString("type");
                        Double lat = Double.valueOf(Accomd_gps_lat);
                        Double lng = Double.valueOf(Accomd_gps_lng);

                        accomodationModel =new DinningModel(id,accomd_type,image,accomd_name,accomd_location,accomd_wevsite,accomd_phone,lat,lng);
                        accomodationModels.add(accomodationModel);
                    }
                    mAdapter.notifyDataSetChanged();

                } catch (JSONException e) {

                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub
//                Toast.makeText(Main2Activity.this,"Error"+error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
        queue2.add(jsObjRequest2);
    }

    public void setAdapter(){
        mAdapter = new DinningAdapter(InformationDetailsDinning.this,accomodationModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

}
