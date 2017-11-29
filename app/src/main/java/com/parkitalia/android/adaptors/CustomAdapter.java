package com.parkitalia.android.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parkitalia.android.R;

/**
 * Created by Indosurplus on 3/30/2017.
 */

public class CustomAdapter extends BaseAdapter
{
    private static LayoutInflater inflater = null;
    Context context;

    public CustomAdapter(Context context)
    {

        /********** Take passed values **********/
        this.context = context;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount()
    {

        return 10;
    }

    public Object getItem(int position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View vi = convertView;
        ViewHolder holder;

        if (convertView == null)
        {

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.customviewnavigation, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text);
            holder.image = (ImageView) vi.findViewById(R.id.image);

            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) vi.getTag();
        }

        return vi;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder
    {

        public TextView text;
        public TextView text1;
        public TextView textWide;
        public ImageView image;

    }

}
