package com.parkitalia.android.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parkitalia.android.R;
import com.parkitalia.android.activities.LandingScreen;

/**
 * Created by pc on 6/21/2017.
 */

public class LocationSearchScreen extends BaseFragment {
    private static final String KEY_PREVIOUS_LOCATION = "KEY_PREVIOUS_LOCATION";

    public static Fragment getFragment(String previousLocation) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PREVIOUS_LOCATION, previousLocation == null ? "" : previousLocation);

        LocationSearchScreen receiversFragment = new LocationSearchScreen();
        receiversFragment.setArguments(bundle);
        return receiversFragment;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.location_search, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = (TextView) view.findViewById(R.id.lastlocation);
        SharedPreferences preferences = getActivity().getSharedPreferences("PlaceShared", Context.MODE_PRIVATE);
        preferences.getString("Place_Lat", null);
        preferences.getString("PlaceAddress", null);

        ImageView backView = (ImageView) view.findViewById(R.id.back_button);
        backView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LandingScreen.class);
                intent.putExtra("Key_Data", "0");
                intent.putExtra("Key_Data1", "1");

                startActivity(intent);
            }
        });
     String daata=   preferences.getString("PlaceAddress", null);
        try {
            if(daata.equals("")==true) {
                Log.e("daata", "equal");
            }
            textView.setText(preferences.getString("PlaceAddress", null));
            textView.setText(preferences.getString("PlaceAddress", null));
            textView.setOnClickListener(new View.OnClickListener() {
              @Override
                public void onClick(View v) {
                  ((LandingScreen) activity).pushAddFragments(AppMapFragmentLastSearch.getFragment(""), true, true);
              }
            });
        }catch (Exception e){
                        textView.setText("No Last Search");

        }



        Log.e("daata",     "///"+preferences.getString("PlaceAddress", null));

//        if (preferences.getString("PlaceAddress", null).equals("") == true) {
//            textView.setText("No Last Search");
//
//        } else {
//
//            textView.setText(preferences.getString("PlaceAddress", null));
//            textView.setText(preferences.getString("PlaceAddress", null));
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    ((LandingScreen) activity).pushAddFragments(AppMapFragmentLastSearch.getFragment(""), true, true);
//
//
//                }
//            });
//        }
    }
}
