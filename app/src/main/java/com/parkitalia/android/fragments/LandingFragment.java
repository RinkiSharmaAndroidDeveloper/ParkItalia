package com.parkitalia.android.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.parkitalia.android.R;
import com.parkitalia.android.activities.BaseActivity;
import com.parkitalia.android.activities.LandingScreen;

public class LandingFragment extends BaseFragment
{
      public static Fragment getFragment()
    {
        Bundle bundle = new Bundle();
        LandingFragment receiversFragment = new LandingFragment();
        receiversFragment.setArguments(bundle);
        return receiversFragment;
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activity = (BaseActivity) getActivity();
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Toast.makeText(getContext(),"You click this",Toast.LENGTH_LONG).show();
                return false;
            }
        });
}
}