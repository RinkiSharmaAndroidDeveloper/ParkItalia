//package com.parkitalia.android.fragments;
//
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.parkitalia.android.R;
//import com.parkitalia.android.locationsearch.AppPlace;
//
///**
// * Created by Indosurplus on 5/5/2017.
// */
//
//public class AppmapFragmentModufy extends BaseFragment implements OnMapReadyCallback{
//        private static final String KEY_PLACE = "KEY_PLACE";
//
//    public static Fragment getFragment(AppPlace appPlace)
//        {
//        Bundle bundle = new Bundle();
//            AppmapFragmentModufy receiversFragment = new AppmapFragmentModufy();
//        if (appPlace != null)
//        {
//            bundle.putSerializable(KEY_PLACE, appPlace);
//        }
//        receiversFragment.setArguments(bundle);
//        return receiversFragment;
//    }
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        return inflater.inflate(R.layout.fragment_map, container, false);
//        SupportMapFragment mapFragment=new SupportMapFragment();
//        getChildFragmentManager().beginTransaction().add(R.id.map_container, mapFragment, "MAPS").commit();
//        mapFragment.getMapAsync(this);
//        getChildFragmentManager().beginTransaction().add(R.id.map_container, mapFragment, "MAPS").commit();
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//    }
//}
