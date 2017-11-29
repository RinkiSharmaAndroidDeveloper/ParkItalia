package com.parkitalia.android.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parkitalia.android.ImageLoader;
import com.parkitalia.android.R;

import java.util.ArrayList;

/**
 * Created by vishal mahajan on 7/31/2017.
 */
public class TopTourAdapter extends RecyclerView.Adapter<TopTourAdapter.MyViewHolder> {
    Context context;
    ImageLoader imageLoader =new ImageLoader();
    ArrayList<DinningModel> dinningModels;
    public TopTourAdapter(Context context,ArrayList<DinningModel> dinningModels) {
        this.context =context;
        this.dinningModels=dinningModels;
    }


    @Override
    public TopTourAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_dinning, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopTourAdapter.MyViewHolder holder, int position) {
        final DinningModel dinningModel = dinningModels.get(position);
        holder.Name.setText(dinningModel.getAccomd_name());
        String data1= String.valueOf(dinningModel.getAccomd_gps_lat());
        String data2= String.valueOf(dinningModel.getAccomd_gps_lng());
        holder.Address.setText(dinningModel.getAccomd_wevsite());
        holder.website.setText(dinningModel.getAccomd_location());
        holder.phone_number.setText(dinningModel.getAccomd_phone());
        holder.type_accomodation.setText("Type :"+dinningModel.getAccomd_type());
        holder.Gps.setText("Take Me There");
        com.squareup.picasso.Picasso.with(context).
                load("http://indotesting.com/parkme/uploads/services/"+dinningModel.getImageId()+"/"+dinningModel.getImage()).
                placeholder(R.mipmap.parklogo).
                into(holder.imageView);

      /*  Bitmap bitmap=imageLoader.DownloadImageFromPath(accomodationModel.getImage());
        holder.imageView.setImageBitmap(bitmap);*/
        holder.Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, DinningWebActivity.class);
                intent.putExtra("Url",dinningModel.getAccomd_location());
                context.startActivity(intent);
            }
        });
        holder.Gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //      String strUri = "http://maps.google.com/maps?q=loc:" + accomodationModel.getAccomd_gps_lat() + "," + accomodationModel.getAccomd_gps_lng()+ " (" + "Label which you want" + ")";
                Intent intent = new Intent(context, AccomodationMapActivity.class);
                intent.putExtra("lat",dinningModel.getAccomd_gps_lat());
                intent.putExtra("lng", dinningModel.getAccomd_gps_lng());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dinningModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Address, website, phone_number, type_accomodation, Gps;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.name);
            Address = (TextView) view.findViewById(R.id.address);
            website = (TextView) view.findViewById(R.id.web_link);
            phone_number = (TextView) view.findViewById(R.id.phone_number);
            type_accomodation = (TextView) view.findViewById(R.id.accomodation_type);
            Gps = (TextView) view.findViewById(R.id.gps_type);
            imageView = (ImageView) view.findViewById(R.id.image);
        }

    }
}
