package com.parkitalia.android.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parkitalia.android.ImageLoader;
import com.parkitalia.android.R;
import com.parkitalia.android.activities.ItalianRoadModel;

import java.util.ArrayList;

/**
 * Created by Android-Dev2 on 5/30/2017.
 */

public class ItalianAdapter extends RecyclerView.Adapter<ItalianAdapter.MyViewHolder> {
    Context context;
    ImageLoader imageLoader = new ImageLoader();
    ArrayList<ItalianRoadModel> accomodationModels;

    public ItalianAdapter(Context context, ArrayList<ItalianRoadModel> accomodationModels) {
        this.context = context;
        this.accomodationModels = accomodationModels;
    }


    @Override
    public ItalianAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.italianadapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItalianAdapter.MyViewHolder holder, int position) {
        final ItalianRoadModel accomodationModel = accomodationModels.get(position);
        holder.Name.setText(accomodationModel.getTitle());
        com.squareup.picasso.Picasso.with(context).
                load("http://indotesting.com/parkme/uploads/rules/"+accomodationModel.getId()+"/"+accomodationModel.getImage()).
                placeholder(R.mipmap.parklogo).
                resize(250,250)
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return accomodationModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Address, website, phone_number, type_accomodation, Gps;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.italian_text);
            imageView = (ImageView) view.findViewById(R.id.italian_image);
        }

    }
}
