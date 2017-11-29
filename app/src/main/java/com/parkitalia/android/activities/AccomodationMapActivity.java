package com.parkitalia.android.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parkitalia.android.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccomodationMapActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String MyPREFERENCES = "LocationLAtLng";
    private GoogleMap mMap;
    Double lat, lng, lat1, latitude, longitude,lati,longi,saveLat,saveLng;
    GoogleMap googleMap1;
    String email;
    LinearLayout findButton, myAccountButton;
    Marker marker;
    Polyline polyline23;
    SharedPreferences sharedpreferences;
    List<LatlongmodelGet> listmodel = new ArrayList<LatlongmodelGet>();
    Boolean isAlreadyHere = true;
    List<LatLng> pointsdecode = new ArrayList<LatLng>();
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomodation_map2);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        findButton = (LinearLayout) findViewById(R.id.button_find_my_car);
        myAccountButton = (LinearLayout) findViewById(R.id.linearlayout_account);
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat", -34);
        lng = intent.getDoubleExtra("lng", 151);

        LocationManager locationManager = (LocationManager) getApplicationContext()
                .getSystemService(getApplicationContext().LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location!=null){
            lati = location.getLatitude();
            longi = location.getLongitude();
        }
        latLng = new LatLng(lat,lng);

        myAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AccomodationMapActivity.this, MyAccount.class);
                startActivity(intent1);
            }
        });
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences1 = getApplication().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String saveLati=sharedpreferences1.getString("Lat","");
                String saveLongi=sharedpreferences1.getString("Lng","");
                try{
                    saveLat=Double.parseDouble(saveLati);
                    saveLng=Double.parseDouble(saveLongi);
                }catch(NumberFormatException e){}

                if(saveLati!=null && saveLongi!=null&& !saveLati.equals("")&& !saveLongi.equals("")) {

                    mMap.addMarker(new MarkerOptions().position(new LatLng(saveLat, saveLng)).title("Your parked car location").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_red)));

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(saveLat, saveLng), 15));
                }else{
                    Toast.makeText(AccomodationMapActivity.this, "First save your location",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));
        mMap.getUiSettings().setMapToolbarEnabled(true);

        SessionManager sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetails();

        String id = user.get(SessionManager.KEY_ID);
        String lname = user.get(SessionManager.Key_Lname);

        String fname = user.get(SessionManager.KeY_Fname);
        email = user.get(SessionManager.KEY_EMAIL);
        String link25 = "http://indotesting.com/parkme/webservices/serachby_userlocation/get_datas";
        Log.e("DataLink", link25);

        RequestQueue queue23 = Volley.newRequestQueue(getApplicationContext());

        StringRequest jsObjRequest22 = new StringRequest(Request.Method.GET, link25,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Log.d("jsonobject gggggg", "response" + response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String details = json.getString("Details");
                            Log.d("jsonobject details", "response" + details);
                            JSONArray jsonArray = new JSONArray(details);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String lat = jsonObject.getString("lat");
                                String lng = jsonObject.getString("lng");
                                String name = jsonObject.getString("name");
                                String type = jsonObject.getString("type");
                                String image = jsonObject.getString("image");

                                Log.e("Daoub", type);
                                listmodel.add(new LatlongmodelGet(id,Double.parseDouble(lat), Double.parseDouble(lng), name, type,image));
                                if (type.contains("MC") == true) {
                                    Log.e("Daoub", "MC");

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mcicon)));
                                }
                                if (type.contains("F") == true) {
                                    Log.e("Daoub", "F");

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.ficon)));
                                }
                                if (type.contains("P") == true) {
                                    Log.e("Daoub", "P");


                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.ppico)));
                                }
                                if (type.contains("RV") == true) {
                                    Log.e("Daoub", "RV");

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.rv)));
                                }
                                if (type.contains("D") == true) {
                                    Log.e("Daoub", "D");

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.dicon)));
                                }
                            }


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
        queue23.add(jsObjRequest22);

        //add functions here

        String link23 = "http://indotesting.com/parkme/webservices/parking/getplace?user_id=" + email;
        Log.e("DataLink", link23);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, link23,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        // TODO Auto-generated method stub
                        Log.d("jsonobject gggggg", "response" + response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String details = json.getString("Details");
                            Log.d("jsonobject details", "response" + details);
                            JSONArray jsonArray = new JSONArray(details);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String lat = jsonObject.getString("lat");
                                String lng = jsonObject.getString("lng");
                                String name = jsonObject.getString("name");
                                String type = jsonObject.getString("type");
                                String image=jsonObject.getString("image");
                                listmodel.add(new LatlongmodelGet(id,Double.parseDouble(lat), Double.parseDouble(lng), name, type,image));
                                if (type.equals("MC")) {
                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mcicon)));
                                }
                                if (type.equals("F")) {

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.ficon)));
                                }
                                if (type.equals("P")) {


                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.ppico)));
                                }
                                if (type.equals("RV")) {

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.rv)));
                                }
                                if (type.equals("D")) {

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.dicon)));
                                } else {
                                }
                            }
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
        mMap.setOnMarkerClickListener(
                new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if (isAlreadyHere) {
                            isAlreadyHere = false;
                            googleMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(lati, longi))
                                    .title("You")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mine)));
                            googleMap.getUiSettings().setMapToolbarEnabled(true);
                            String link6 = "https://maps.googleapis.com/maps/api/directions/json?origin=" + lati + "," + longi + "&destination=" + lat + "," + lng + "&mode=car&key=AIzaSyAtUvQTirIhpDUe5XbOHogeSgzFymxv0oc";
                            Log.e("Link 67", link6);
                            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                            final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, link6, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.e("Json Object", "response:=" + response);
                                    try {
                                        String routes = response.getString("routes");

                                        JSONArray jsonArray = new JSONArray(routes);
                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                                            String bounds = jsonObject.getString("bounds");
                                            String legspoint = jsonObject.getString("legs");
                                            JSONArray legsArr = new JSONArray(legspoint);
                                            for (int j = 0; j < legsArr.length(); j++) {

                                                JSONObject legsobj = legsArr.getJSONObject(j);
                                                String distanceObj = legsobj.getString("distance");
                                                JSONObject jstextDistance = new JSONObject(distanceObj);
                                                String distancefinal = jstextDistance.getString("text");
                                                Log.e("Distance Meter", distancefinal);
                                                //   nametext.setText("TAKE ME HERE "+distancefinal);
                                            }

                                            String overviewpoints = jsonObject.getString("overview_polyline");
                                            Log.e("Overviewpoints", overviewpoints);
                                            JSONObject jsonObjectpoints = new JSONObject(overviewpoints);
                                            String points = jsonObjectpoints.getString("points");
                                            Log.e("points", points);
                                            pointsdecode = decodePolyLine(points);


                                            JSONObject jsonObject1 = new JSONObject(bounds);
                                            String northeast = jsonObject1.getString("northeast");
                                            JSONObject jsonObject2 = new JSONObject(northeast);
                                            lat1 = jsonObject2.getDouble("lat");
                                            double long1 = jsonObject2.getDouble("lng");
                                            LatLng sydney2 = new LatLng(lat1, long1);


                                            String southwest = jsonObject1.getString("southwest");
                                            JSONObject jsonObject3 = new JSONObject(southwest);

                                        }

                                        Log.e("Pointdecode", pointsdecode.toString() + pointsdecode.size());
                                        PolylineOptions polylineOptions = new PolylineOptions().
                                                geodesic(true).
                                                color(Color.BLUE).
                                                width(10);
                                        for (int j = 0; j < pointsdecode.size(); j++) {
//
////                        mMap.addMarker(new MarkerOptions().position(pointsdecode.get(j)).title("Place B"));
//
//
                                            PolylineOptions polylineOptions2 = polylineOptions.add(pointsdecode.get(j));
                                            polyline23 = googleMap.addPolyline(polylineOptions);
//
                                        }
//
//                    polylineOptions.visible(false);
//                    Polyline line = mMap.addPolyline(new PolylineOptions()
//                            .add(new LatLng(location.getLatitude(), location.getLongitude()),
//                                    new LatLng(this.destinationLatitude, this.destinationLongitude))
//                            .width(1)
//                            .color(Color.DKGRAY)
                                        polyline23.remove();

                                        Log.e("Pointdecode Clear", pointsdecode.toString() + pointsdecode.size());


                                        googleMap.addPolyline(polylineOptions);

                                        Log.e("routes", routes);
                                    } catch (Exception e) {
                                        e.printStackTrace();
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

                        } else {
                            Toast.makeText(AccomodationMapActivity.this, "You are already here", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }

                });
    }
    private void getMyLocation() {

        /*LocationManager locationManager = (LocationManager) getApplicationContext()
                .getSystemService(getApplicationContext().LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lati = location.getLatitude();
        longi = location.getLongitude();
        LatLng latLng = new LatLng(lati,longi);
        *//*CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        googleMap1.animateCamera(CameraUpdateFactory.zoomIn());
        //
        googleMap1.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));*/

    }


    private List<LatLng> decodePolyLine(final String poly) {
        int len = poly.length();
        int index = 0;
        List<LatLng> decoded = new ArrayList<LatLng>();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b;
            int shift = 0;
            int result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = poly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            decoded.add(new LatLng(
                    lat / 100000d, lng / 100000d
            ));
        }

        return decoded;
    }
}