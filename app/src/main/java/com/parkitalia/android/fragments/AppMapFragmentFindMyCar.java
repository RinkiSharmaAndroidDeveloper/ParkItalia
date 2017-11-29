package com.parkitalia.android.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.parkitalia.android.activities.BaseActivity;
import com.parkitalia.android.activities.LandingScreen;
import com.parkitalia.android.activities.LatLongModel;
import com.parkitalia.android.activities.LatlongmodelGet;
import com.parkitalia.android.activities.MyAccount;
import com.parkitalia.android.activities.SessionManager;
import com.parkitalia.android.locationsearch.AppPlace;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppMapFragmentFindMyCar extends BaseFragment implements OnMapReadyCallback
{
    double lat1,long1,lat2,long2;

    List<Polyline> polylines = new ArrayList<Polyline>();
    Polyline polylineFinal;
    TextView textDistance,nametext;
    List<LatlongmodelGet>listmodel=new ArrayList<LatlongmodelGet>();
    List<LatLng> pointsdecode=new ArrayList<LatLng>();
    List<LatLongModel>objList=new ArrayList<LatLongModel>();
    String link="https://maps.googleapis.com/maps/api/directions/json?origin=Honda%202w%20Zonal%20office&destination=LANDMARK%20Designer%20Studio&mode=car&key=AIzaSyDzzJDHFB6rZ-GT56iqEgqY-wQSiU8t_f4";
    String link2="https://maps.googleapis.com/maps/api/directions/json?origin=43.272910,11.990023&destination=43.272901,11.984020&mode=car&key=AIzaSyDzzJDHFB6rZ-GT56iqEgqY-wQSiU8t_f4";

    private static final String KEY_PLACE = "KEY_PLACE";
     Polyline polyline23;
     String email;

    public static Fragment getFragment(AppPlace appPlace)
    {
        Bundle bundle = new Bundle();
        AppMapFragmentFindMyCar receiversFragment = new AppMapFragmentFindMyCar();
        if (appPlace != null)
        {
            bundle.putSerializable(KEY_PLACE, appPlace);
        }
        receiversFragment.setArguments(bundle);
        return receiversFragment;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = new SupportMapFragment();
//        FloatingActionButton buttonfloat= (FloatingActionButton) view.findViewById(R.id.fab);
//        buttonfloat.setVisibility(View.INVISIBLE);
        getChildFragmentManager().beginTransaction().add(R.id.map_container, mapFragment, "MAPS").commit();
        mapFragment.getMapAsync(this);
        nametext= (TextView) view.findViewById(R.id.fragment_map_tv_take_me);
        textDistance= (TextView) view.findViewById(R.id.fragment_map_tv_distance);
        ImageView textView= (ImageView) view.findViewById(R.id.back_button);
        LinearLayout myAccount= (LinearLayout) view.findViewById(R.id.linearlayout_account);
        LinearLayout findMyCarPhoto= (LinearLayout) view.findViewById(R.id.find_my_car_layout);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LandingScreen.class);
                intent.putExtra("Key_Data","0");
                intent.putExtra("Key_Data1","1");

                startActivity(intent);
            }
        });

        myAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), MyAccount.class);
                getActivity().startActivity(intent);

            }
        });

        findMyCarPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(),"You are already here",Toast.LENGTH_SHORT).show();
            }
        });
    }

    Marker marker;
    GoogleMap googleMap;

    @Override
    public void onMapReady(final GoogleMap googleMap)
    {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(43.272910, 11.990023)).title("You").icon(BitmapDescriptorFactory.fromResource(R.drawable.mine)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(43.272910, 11.990023),15));
        // Zoom in, animating the camera.
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
        SessionManager sessionManager=new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetails();
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        String id=      user.get(SessionManager.KEY_ID);
        String lname=     user.get(SessionManager.Key_Lname);

        String fname=   user.get(SessionManager.KeY_Fname);
         email= user.get(SessionManager.KEY_EMAIL);
        String link23="http://indotesting.com/parkme/webservices/parking/getplace?user_id="+email;
        Log.e("DataLink",link23);

        RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest jsObjRequest = new StringRequest(Request.Method.GET, link23,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response)
                    {
                        // TODO Auto-generated method stub
                        Log.d("jsonobject gggggg", "response" + response);
                        try
                        {
                            JSONObject json = new JSONObject(response);
                            String details=json.getString("Details");
                            Log.d("jsonobject details", "response" + details);
                            JSONArray jsonArray=new JSONArray(details);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String id = jsonObject.getString("id");
                                String lat=   jsonObject.getString("lat");
                                String lng=jsonObject.getString("lng");
                                String name=jsonObject.getString("name");
                                String type=jsonObject.getString("type");
                                String image = jsonObject.getString("image");
                                listmodel.add(new LatlongmodelGet(id,Double.parseDouble(lat),Double.parseDouble(lng),name,type,image));
                                if(type.equals("MC")) {
                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mcicon)));
                                }
                                if(type.equals("F")) {

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.ficon)));
                                }
                                if(type.equals("P")) {


                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.ppico)));
                                }
                                if(type.equals("RV")) {

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.rv)));
                                }
                                if(type.equals("D")) {

                                    googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title(name).icon(BitmapDescriptorFactory.fromResource(R.drawable.dicon)));
                                }
                                else {

                                }
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

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                googleMap.clear();

                textDistance.setText("");
                for(int i=0;i<listmodel.size();i++){
                    Log.e("Type Model",listmodel.get(i).getType());
                    if(listmodel.get(i).getType().equals("MC")) {
                        nametext.setText("TAKE ME HERE From :"+ listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mcicon)));
                    }
                    if(listmodel.get(i).getType().equals("F")) {
                        nametext.setText("TAKE ME HERE From :"+ listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ficon)));
                    }
                    if(listmodel.get(i).getType().equals("P")) {
                        nametext.setText("TAKE ME HERE From :"+ listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.ppico)));
                    }
                    if(listmodel.get(i).getType().equals("D")) {
                        nametext.setText("TAKE ME HERE From :"+ listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.dicon)));
                    }
                    if(listmodel.get(i).getType().equals("RV")) {
                        nametext.setText("TAKE ME HERE From :"+ listmodel.get(i).getName());

                        googleMap.addMarker(new MarkerOptions()
                                .position(new LatLng(listmodel.get(i).getLat(), listmodel.get(i).getLng()))
                                .title(listmodel.get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.rv)));
                    }
                }
                googleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(43.272910, 11.990023))
                        .title("You")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.mine)));
                Log.e("long",""+marker.getPosition().longitude);
                String link6="https://maps.googleapis.com/maps/api/directions/json?origin="+43.272910+","+11.990023+"&destination="+marker.getPosition().latitude+","+marker.getPosition().longitude+"&mode=car&key=AIzaSyAtUvQTirIhpDUe5XbOHogeSgzFymxv0oc";
                Log.e("Link 67",link6);





                RequestQueue queue = Volley.newRequestQueue(getActivity());
                final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, link6, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Json Object","response:="+response);
                        try {
                            String routes=response.getString("routes");

                            JSONArray jsonArray=new JSONArray(routes);
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                String bounds=jsonObject.getString("bounds");
                                String legspoint=jsonObject.getString("legs");
                                JSONArray legsArr= new JSONArray(legspoint);
                                for(int j=0;j<legsArr.length();j++){

                                    JSONObject legsobj=legsArr.getJSONObject(j);
                                    String distanceObj=legsobj.getString("distance");
                                    JSONObject jstextDistance=new JSONObject(distanceObj);
                                    String distancefinal=jstextDistance.getString("text");
                                Log.e("Distance Meter",distancefinal);
                                    textDistance.setText(distancefinal);
                                }

                                String overviewpoints=jsonObject.getString("overview_polyline");
                                Log.e("Overviewpoints",overviewpoints);
                                JSONObject jsonObjectpoints=new JSONObject(overviewpoints);
                                String points=jsonObjectpoints.getString("points");
                                Log.e("points",points);
                                pointsdecode = decodePolyLine(points);


                                JSONObject jsonObject1=new JSONObject(bounds);
                                String northeast=      jsonObject1.getString("northeast");
                                JSONObject jsonObject2=new JSONObject(northeast);
                                lat1= jsonObject2.getDouble("lat");
                                double  long1=jsonObject2.getDouble("lng");
                                LatLng sydney2 = new LatLng(lat1,long1);


                                String southwest= jsonObject1.getString("southwest");
                                JSONObject jsonObject3=new JSONObject(southwest);
                            }
                            Log.e("Pointdecode",pointsdecode.toString()+pointsdecode.size());
                            PolylineOptions polylineOptions = new PolylineOptions().
                                    geodesic(true).
                                    color(Color.BLUE).
                                    width(10);
                            for(int j=0;j<pointsdecode.size();j++){
//
////                        mMap.addMarker(new MarkerOptions().position(pointsdecode.get(j)).title("Place B"));
//
//
                                PolylineOptions polylineOptions2=   polylineOptions.add(pointsdecode.get(j));
                                polyline23=googleMap.addPolyline(polylineOptions);
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

                            Log.e("Pointdecode Clear",pointsdecode.toString()+pointsdecode.size());
                            googleMap.addPolyline(polylineOptions);

                            Log.e("routes",routes);
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
