package com.parkitalia.android.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parkitalia.android.ImageLoader;
import com.parkitalia.android.R;

import java.util.ArrayList;

/**
 * Created by Android-Dev2 on 5/30/2017.
 */

public class ServiceAdaapterr extends RecyclerView.Adapter<ServiceAdaapterr.MyViewHolder> {
    Context context;
    ImageLoader imageLoader =new ImageLoader();
    ArrayList<ServiceModel> accomodationModels;
    public ServiceAdaapterr(Context context, ArrayList<ServiceModel> accomodationModels) {
        this.context =context;
        this.accomodationModels=accomodationModels;
    }


    @Override
    public ServiceAdaapterr.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_service, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServiceAdaapterr.MyViewHolder holder, int position) {
    final ServiceModel accomodationModel = accomodationModels.get(position);
        holder.Name.setText(accomodationModel.getTitle());

        holder.Address.setText(accomodationModel.getAddress());
        Log.e("Link","http://indotesting.com/parkme/uploads/services/"+accomodationModel.getId()+"/"+accomodationModel.getImage());
        holder.Gps.setText("Take Me There");
        com.squareup.picasso.Picasso.with(context).
                    load("http://indotesting.com/parkme/uploads/services/"+accomodationModel.getId()+"/"+accomodationModel.getImage()).
                    placeholder(R.mipmap.parklogo).
               into(holder.imageView);

      /*  Bitmap bitmap=imageLoader.DownloadImageFromPath(accomodationModel.getImage());
        holder.imageView.setImageBitmap(bitmap);*/
//        holder.Address.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(context, AccomodationWebViewActivity.class);
//                intent.putExtra("Url",accomodationModel.getAccomd_location());
//                context.startActivity(intent);
//            }
//        });
         holder.Gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //      String strUri = "http://maps.google.com/maps?q=loc:" + accomodationModel.getAccomd_gps_lat() + "," + accomodationModel.getAccomd_gps_lng()+ " (" + "Label which you want" + ")";
                Intent intent = new Intent(context, AccomodationMapActivity.class);
                intent.putExtra("lat",accomodationModel.getLat());
                intent.putExtra("lng", accomodationModel.getLng());
                context.startActivity(intent);
            }
        });


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
