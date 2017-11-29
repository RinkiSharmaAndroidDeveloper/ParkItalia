package com.parkitalia.android.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.parkitalia.android.activities.BaseActivity;
import com.parkitalia.android.activities.LandingScreen;
import com.parkitalia.android.activities.LatLongModel;
import com.parkitalia.android.activities.LatlongmodelGet;
import com.parkitalia.android.activities.MyAccount;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by pc on 6/21/2017.
 */

public class AppMapFragmentLastSearch extends BaseFragment implements OnMapReadyCallback {

    GoogleMap googleMap1;
    double lat1, long1, lat2, long2,lati,longi,saveLat,saveLng;
    public static final String MyPREFERENCES = "LocationLAtLng";
    List<Polyline> polylines = new ArrayList<Polyline>();
    Polyline polylineFinal;
    TextView textDistance, nametext;
    List<LatlongmodelGet> listmodel = new ArrayList<LatlongmodelGet>();
    List<LatLng> pointsdecode = new ArrayList<LatLng>();
    List<android.location.Address> addresses;
    ImageView maptype;
    LatLng datalatlng;
    Double datalat,datalong;

    Geocoder geocoder;
    List<LatLongModel> objList = new ArrayList<LatLongModel>();
    String link = "https://maps.googleapis.com/maps/api/directions/json?origin=Honda%202w%20Zonal%20office&destination=LANDMARK%20Designer%20Studio&mode=car&key=AIzaSyDzzJDHFB6rZ-GT56iqEgqY-wQSiU8t_f4";
    String link2 = "https://maps.googleapis.com/maps/api/directions/json?origin=43.272910,11.990023&destination=43.272901,11.984020&mode=car&key=AIzaSyDzzJDHFB6rZ-GT56iqEgqY-wQSiU8t_f4";

    private static final String KEY_PLACE = "KEY_PLACE";
    Polyline polyline23;
    String email;
    String distancefinal;
    LatLng latLng;
    Button saveButton;
    TextView markerAddress;
    SharedPreferences sharedpreferences;
    LinearLayout findMyCarButton, myAccount;

    int k = 0;

    public static Fragment getFragment(String previousLocation) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PLACE, previousLocation == null ? "" : previousLocation);

        AppMapFragmentLastSearch receiversFragment = new AppMapFragmentLastSearch();
        receiversFragment.setArguments(bundle);
        return receiversFragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_lastsearch, container, false);

       /* SharedPreferences preferences = getActivity().getSharedPreferences("PlaceShared", Context.MODE_PRIVATE);
        String data = preferences.getString("Place_Lat", null);
        String data2 = preferences.getString("Place_long", null);
        Log.e("Data123","=="+data+"//"+data2);*/
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        googleMap1 = googleMap;
       /* maptype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (k % 2 == 0) {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    maptype.setImageResource(R.drawable.mapsatellite);
                } else {
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    maptype.setImageResource(R.drawable.mapnormal);
                }
                k++;

            }
        });*/
        SharedPreferences preferences = getActivity().getSharedPreferences("PlaceShared", Context.MODE_PRIVATE);
        String data = preferences.getString("Place_Lat", null);
        String data2 = preferences.getString("Place_long", null);
        Log.e("Data123","=="+data+"//"+data2);
       // if(data!=null&&data2!=null) {
            datalat = Double.valueOf(data);
            datalong = Double.valueOf(data2);
            datalatlng = new LatLng(datalat, datalong);
            preferences.getString("PlaceAddress", null);
        if (googleMap!= null) {
            LocationManager locationManager = (LocationManager) getContext()
                    .getSystemService(getContext().LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lati = location.getLatitude();
            longi = location.getLongitude();

            googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

                @Override
                public void onMyLocationChange(Location arg0) {
                    // TODO Auto-generated method stub

                        /*googleMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.mine)));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 15));
                        // Zoom in, animating the camera.
                        googleMap.animateCamera(CameraUpdateFactory.zoomIn());*//**//*
                        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);*/

                }
            });
        }
            googleMap.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.mine)));
            googleMap.addMarker(new MarkerOptions().position(datalatlng).title("address").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_red)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 15));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(datalatlng, 15));
            googleMap.getUiSettings().setMapToolbarEnabled(true);
            // Zoom in, animating the camera.
            googleMap.animateCamera(CameraUpdateFactory.zoomIn());
            // Zoom out to zoom level 10, animating with a duration of 2 seconds.
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    return false;
                }
            });
      //  }


        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                getAddressLatLng(latLng);
                googleMap.clear();
                addresses = null;
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove("Lat");
                editor.remove("Lng");

                editor.commit();
                googleMap.addMarker(new MarkerOptions().position(latLng).title("address").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_red)));

                Double lat = marker.getPosition().latitude;
                Double lng = marker.getPosition().longitude;

                for (int i = 0; i < listmodel.size(); i++) {
                    Log.e("Type Model", listmodel.get(i).getType());
                    if (listmodel.get(i).getType().contains("MC")) {
                        nametext.setText("Distance :" + listmodel.get(i).getName());
                        //    Picasso.with(getActivity().getApplicationContext()).load(listmodel.get(i).getImage()).into(maptype);
                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mcicon)));
                        // getAddressLatLng(listmodel.get(i).getLat(),listmodel.get(i).getLng());
                    }
                    if (listmodel.get(i).getType().contains("F")) {
                        nametext.setText("Distance :" + listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ficon)));
                        // getAddressLatLng(listmodel.get(i).getLat(),listmodel.get(i).getLng());
                    }
                    if (listmodel.get(i).getType().contains("P")) {
                        nametext.setText("Distance :" + listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ppico)));
                        //getAddressLatLng(listmodel.get(i).getLat(),listmodel.get(i).getLng());
                    }
                    if (listmodel.get(i).getType().contains("D")) {
                        nametext.setText("Distance :" + listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.dicon)));
                        //getAddressLatLng(listmodel.get(i).getLat(),listmodel.get(i).getLng());
                    }
                    if (listmodel.get(i).getType().contains("RV")) {
                        nametext.setText("Distance :" + listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.rv)));
                        // getAddressLatLng(listmodel.get(i).getLat(),listmodel.get(i).getLng());
                    }

                }

                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(lati,longi))
                        .title("You")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mine)));
                Log.e("long", "" + marker.getPosition().longitude);
                String link6 = "https://maps.googleapis.com/maps/api/directions/json?origin=" + lati + "," + longi + "&destination=" + marker.getPosition().latitude + "," + marker.getPosition().longitude + "&mode=car&key=AIzaSyAtUvQTirIhpDUe5XbOHogeSgzFymxv0oc";
                Log.e("Link 67", link6);
                RequestQueue queue = Volley.newRequestQueue(getActivity());
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
                                    nametext.setText("Distance:" + distancefinal);
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


                return false;
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = new SupportMapFragment();
        getChildFragmentManager().beginTransaction().add(R.id.map_container, mapFragment, "MAPS").commit();
        mapFragment.getMapAsync(this);
    saveButton = (Button) view.findViewById(R.id.button_save);
        markerAddress = (TextView) view.findViewById(R.id.fragment_map_tv_distance);
        nametext = (TextView) view.findViewById(R.id.fragment_map_tv_take_me);
        findMyCarButton = (LinearLayout) view.findViewById(R.id.find_my_car_layout);
      // saveButton.setVisibility(View.GONE);
        maptype = (ImageView) view.findViewById(R.id.clickmaptype);
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        ImageView backview= (ImageView) view.findViewById(R.id.back_button);

        backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LandingScreen.class);
                intent.putExtra("Key_Data", "0");
                intent.putExtra("Key_Data1", "1");

                startActivity(intent);

            }
        });

        LinearLayout layoutScr= (LinearLayout) view.findViewById(R.id.find_my_car_layout);
        layoutScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            getPolyline();

                }
        });
        LinearLayout    myAccount = (LinearLayout) view.findViewById(R.id.linearlayout_account);

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity().getApplicationContext(), MyAccount.class);
                getActivity().startActivity(intent);
            }
        });

        findMyCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getPolyline();
                SharedPreferences sharedpreferences1 = getActivity().getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String saveLati = sharedpreferences1.getString("Lat", "");
                String saveLongi = sharedpreferences1.getString("Lng", "");
                try{
                    saveLat = Double.parseDouble(saveLati);
                    saveLng = Double.parseDouble(saveLongi);
                }catch(NumberFormatException e){}

                if(saveLati!=null && saveLongi!=null) {

                    googleMap1.addMarker(new MarkerOptions().position(new LatLng(saveLat, saveLng)).title("Your parked car location").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_icon_red)));

                    googleMap1.getUiSettings().setMapToolbarEnabled(true);
                    // Zoom in, animating the camera.
                    googleMap1.animateCamera(CameraUpdateFactory.zoomIn());
                    // Zoom out to zoom level 10, animating with a duration of 2 seconds.
                    googleMap1.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "First save your location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void getPolyline() {
        getAddressLatLng(datalatlng);
        String link6 = "https://maps.googleapis.com/maps/api/directions/json?origin=" +lati+ "," +longi+ "&destination=" + datalatlng.latitude + "," + datalatlng.longitude + "&mode=car&key=AIzaSyAtUvQTirIhpDUe5XbOHogeSgzFymxv0oc";
        Log.e("Link 67", link6);


        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
                            distancefinal= jstextDistance.getString("text");
                            Log.e("Distance Meter", distancefinal);
                            //  textDistance.setText(distancefinal);
                        }
                        nametext.setText("Distance: "+distancefinal);

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
                        polyline23 = googleMap1.addPolyline(polylineOptions);
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


                    googleMap1.addPolyline(polylineOptions);

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
        getAddressLatLng(datalatlng);


    }

    public void getAddressLatLng(final LatLng latLng) {
        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                String placeName = strReturnedAddress.toString();

                markerAddress.setText(placeName);
                saveButton.setVisibility(View.VISIBLE);
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("Lat", String.valueOf(lati));
                        editor.putString("Lng", String.valueOf(longi));
                        editor.commit();
                        Toast.makeText(getActivity().getApplicationContext(), "Your location has been saved", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
