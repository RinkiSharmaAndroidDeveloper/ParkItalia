package com.parkitalia.android.activities;

import android.content.Intent;
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
import java.util.List;

/**
 * Created by vishal mahajan on 7/28/2017.
 */
public class ServicesMapActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String MyPREFERENCES = "LocationLAtLng";
    private GoogleMap mMap;
    Double lat, lng, lat1, latitude, longitude;
    LinearLayout findButton, myAccountButton;
    Marker marker;
    Polyline polyline23;
    Boolean isAlreadyHere = true;
    List<LatLng> pointsdecode = new ArrayList<LatLng>();
    double lati,longi;

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

        myAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ServicesMapActivity.this, MyAccount.class);
                startActivity(intent1);
            }
        });
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(sydney).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));
        mMap.getUiSettings().setMapToolbarEnabled(true);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAlreadyHere) {
                    isAlreadyHere = false;
                    LocationManager locationManager = (LocationManager) getApplication()
                            .getSystemService(getApplication().LOCATION_SERVICE);
                    Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    lati = location.getLatitude();
                    longi = location.getLongitude();
                    LatLng latLng = new LatLng(lati,longi);
                    googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(lati, longi))
                            .title("You")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mine)));
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
                    Toast.makeText(ServicesMapActivity.this, "You are already here", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

