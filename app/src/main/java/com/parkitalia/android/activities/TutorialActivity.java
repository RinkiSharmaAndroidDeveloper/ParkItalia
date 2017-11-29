package com.parkitalia.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.parkitalia.android.R;
import com.parkitalia.android.extra.App;
import com.parkitalia.android.fragments.tutorial1;
import com.parkitalia.android.fragments.tutorial2;
import com.parkitalia.android.fragments.tutorial3;
import com.parkitalia.android.fragments.tutorial4;

public class TutorialActivity extends AppCompatActivity
{
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutorial);


        //........intialisation.............//

        init();
    }

    private void init()
    {

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter
    {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount()
        {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position)
        {
            switch (position)
            {
                case 0: // Fragment # 0 - This will show FirstFragment
                    tutorial2 t = new tutorial2();
                    return t;
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    tutorial3 t2 = new tutorial3();
                    return t2;
                case 2: // Fragment # 1 - This will show SecondFragment
                    tutorial4 t3 = new tutorial4();
                    return t3;

                case 3: // Fragment # 1 - This will show SecondFragment
                    tutorial1 t4 = new tutorial1();
                    return t4;
                default:
                    return null;
            }
        }

//        // Returns the page title for the top indicator
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return "Page " + position;
//        }
//
//    }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //    finish();

    }
}

