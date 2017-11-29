package com.parkitalia.android.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parkitalia.android.R;
import com.parkitalia.android.activities.LandingScreen;
import com.parkitalia.android.activities.MyAccount;
import com.parkitalia.android.locationsearch.LocationSearchActivity;

public class DashboardScreen extends BaseFragment implements View.OnClickListener
{
    private String provider;
    private LocationManager locationManager;
    private TextView tvLocationName,txViewByZipcode;
    private TextView tvLastLocation;



    public static Fragment getFragment()
    {
        Bundle bundle = new Bundle();
        DashboardScreen receiversFragment = new DashboardScreen();
        receiversFragment.setArguments(bundle);
        return receiversFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.dashboard_location_by_current_location).setOnClickListener(this);
        view.findViewById(R.id.dashboard_location_by_name).setOnClickListener(this);
        view.findViewById(R.id.search_by_zipcode).setOnClickListener(this);
        view.findViewById(R.id.lastSearchScreen).setOnClickListener(this);

        /*tvLocationName = (TextView) view.findViewById(R.id.dashboard_location_tv_location_name);
        tvLastLocation = (TextView) view.findViewById(R.id.dashboard_location_tv_last_searched_location);*/
        LinearLayout findmycar= (LinearLayout) view.findViewById(R.id.linearlayout_account);
        LinearLayout findmycarscreen= (LinearLayout) view.findViewById(R.id.findmycar);
        findmycarscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LandingScreen) activity).pushAddFragments(AppMapFragmentFindMyCar.getFragment(null), true, true);

            }
        });
        findmycar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), MyAccount.class);
                getActivity().startActivity(intent);

            }
        });

    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.dashboard_location_by_current_location:
                ((LandingScreen) activity).pushAddFragments(AppMapFragment.getFragment(null), true, true);
                break;
            case R.id.dashboard_location_by_name:
                ((LandingScreen) activity).pushAddFragments(LocationSearchActivity.getFragment(""), true, true);
                break;
            case R.id.search_by_zipcode:
                ((LandingScreen) activity).pushAddFragments(LocationSearchActivity.getFragment(""), true, true);
                break;
            case R.id.lastSearchScreen:

                SharedPreferences preferences = getActivity().getSharedPreferences("PlaceShared", Context.MODE_PRIVATE);
                preferences.getString("Place_Lat", null);
                preferences.getString("PlaceAddress", null);
                String data = preferences.getString("PlaceAddress", null);
                try{
                    if(data.equals("")!=true) {
                    Log.e("daata", "equal");
                    }
                    ((LandingScreen) activity).pushAddFragments(AppMapFragmentLastSearch.getFragment(""), true, true);

                }catch (Exception e){

                    Toast.makeText(getActivity().getApplicationContext(),"No last search",Toast.LENGTH_SHORT).show();
                }

                break;

        }

    }


    @Override
    public void onResume()
    {
        super.onResume();

    }

    @Override
    public void onPause()
    {
        super.onPause();
    }



    }


