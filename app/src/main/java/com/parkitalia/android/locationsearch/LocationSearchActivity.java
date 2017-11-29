package com.parkitalia.android.locationsearch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.parkitalia.android.R;
import com.parkitalia.android.activities.LandingScreen;
import com.parkitalia.android.fragments.AppMapFragment23;
import com.parkitalia.android.fragments.BaseFragment;

import static android.content.Context.MODE_PRIVATE;

//import com.parkitalia.android.fragments.AppMapFragment;

/**
 * This activity is used to show forget password screen. <br/>
 */
public class LocationSearchActivity extends BaseFragment implements TextWatcher, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    /**
     * The constant KEY_INTENT_CODE.
     */
    public static final int KEY_INTENT_CODE = 1025;
    /**
     * The constant KEY_PREVIOUS_LOCATION.
     *
     */
    EditText etLocationSearch;
    private static final String KEY_PREVIOUS_LOCATION = "KEY_PREVIOUS_LOCATION";
    /**
     * The List adapter.
     */
    private LocationListAdapter listAdapter;
    /**
     * The Filter.
     */
    private Filter filter;
    LandingScreen landingScreen;
    /**
     * The Api client.
     */
    private GoogleApiClient apiClient;

    /**
     * Launch activity.
     *
     * @param previousLocation the previous location
     */
    public static Fragment getFragment(String previousLocation) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PREVIOUS_LOCATION, previousLocation == null ? "" : previousLocation);

        LocationSearchActivity receiversFragment = new LocationSearchActivity();
        receiversFragment.setArguments(bundle);
        return receiversFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_location_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listAdapter = new LocationListAdapter(activity);
        filter = listAdapter.getFilter();
        initialiseView(view);

        apiClient = new GoogleApiClient.Builder(activity).addApi(Places.GEO_DATA_API)
                .enableAutoManage((LandingScreen) activity, 0, this)
                .addConnectionCallbacks(this).build();
        ImageView textView = (ImageView) view.findViewById(R.id.back_button);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LandingScreen.class);
                intent.putExtra("Key_Data", "0");
                intent.putExtra("Key_Data1", "1");

                startActivity(intent);
            }
        });
    }

    /**
     * This method is used to initialise views and set there actions
     */
    private void initialiseView(View view) {
//        setHeader(getString(R.string.location_search_screen_title));
        ListView listView = (ListView) view.findViewById(R.id.activity_location_search_lv_locations);
        listView.setOnItemClickListener(new NotificationItemClickListener());
        listView.setAdapter(listAdapter);
         etLocationSearch = (EditText) view.findViewById(R.id.activity_location_search_et_location);
        etLocationSearch.addTextChangedListener(this);
        etLocationSearch.setText(getArguments().getString(KEY_PREVIOUS_LOCATION));
        etLocationSearch.setSelection(etLocationSearch.getText().length());
        filter.filter(etLocationSearch.getText().toString());
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }


    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        filter.filter(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        listAdapter.setGoogleApiClient(apiClient);
        filter.filter(getArguments().getString(KEY_PREVIOUS_LOCATION));
    }

    @Override
    public void onConnectionSuspended(int i) {
        listAdapter.setGoogleApiClient(null);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(activity, "Google Places API connection failed with error code:" + connectionResult.getErrorCode(), Toast.LENGTH_LONG).show();
    }


    class NotificationItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            LocationListAdapter.LocationViewHolder holder = (LocationListAdapter.LocationViewHolder) view.getTag();

            Places.GeoDataApi.getPlaceById(apiClient, holder.location.getPlaceId()).setResultCallback(new LatLgnListener(holder.location));
        }

    }


    class LatLgnListener implements ResultCallback<PlaceBuffer> {

        LocationListAdapter.PlaceAutocomplete placeAutocomplete;

        public LatLgnListener(LocationListAdapter.PlaceAutocomplete placeAutocomplete) {
            this.placeAutocomplete = placeAutocomplete;
        }

        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (places.getStatus().isSuccess() && places.getCount() > 0) {
                Place myPlace = places.get(0);
                SharedPreferences prefs = getActivity().getSharedPreferences("PlaceShared", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Place_Lat_long", String.valueOf(myPlace.getLatLng()));
                editor.putString("Place_Lat", String.valueOf(myPlace.getLatLng().latitude));
                editor.putString("Place_long", String.valueOf(myPlace.getLatLng().longitude));

                editor.putString("PlaceAddress", String.valueOf(myPlace.getAddress()));
                editor.commit();
                Log.e("myplace",myPlace.getName()+"//"+myPlace.getLatLng()+"//"+myPlace.getAddress());
                final LatLng location=places.get(0).getLatLng();
                landingScreen =new LandingScreen();
                landingScreen.getLatLngSearchLoc(location);
                AppPlace appPlace = new AppPlace(myPlace, placeAutocomplete.getFirstText(), placeAutocomplete.getSecondaryText());
                ((LandingScreen) activity).pushAddFragments(AppMapFragment23.getFragment(appPlace,location), true, true);
                InputMethodManager keyboard = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.showSoftInput(etLocationSearch, 0);
                keyboard.hideSoftInputFromWindow(etLocationSearch.getWindowToken(), 0);
            }
            places.release();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        apiClient.stopAutoManage(getActivity());
        apiClient.disconnect();
    }
}
