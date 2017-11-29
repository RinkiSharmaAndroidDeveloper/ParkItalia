package com.parkitalia.android.adaptors;

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
import com.parkitalia.android.activities.ServicesMapActivity;
import com.parkitalia.android.activities.ServicesModel;
import com.parkitalia.android.fragments.ServicesWebView;

import java.util.ArrayList;

/**
 * Created by vishal mahajan on 7/28/2017.
 */
public class ServicesApapter extends RecyclerView.Adapter<ServicesApapter.MyViewHolder> {
    Context context;
    ImageLoader imageLoader =new ImageLoader();
    ArrayList<ServicesModel> servicesModels;
    public ServicesApapter(Context context,ArrayList<ServicesModel> servicesModels) {
        this.context =context;
        this.servicesModels=servicesModels;
    }


    @Override
    public ServicesApapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_services, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServicesApapter.MyViewHolder holder, int position) {
        final ServicesModel servicesModel = servicesModels.get(position);
        holder.Name.setText(servicesModel.getAccomd_name());
        String data1= String.valueOf(servicesModel.getAccomd_gps_lat());
        String data2= String.valueOf(servicesModel.getAccomd_gps_lng());
        holder.Address.setText(servicesModel.getAccomd_wevsite());
        holder.website.setText(servicesModel.getAccomd_location());
        holder.phone_number.setText(servicesModel.getAccomd_phone());
        holder.Gps.setText("Take Me There");
        if(servicesModel.getImageId()!=null) {
            holder.defaultImage.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            com.squareup.picasso.Picasso.with(context).
                    load("http://indotesting.com/parkme/uploads/services/" + servicesModel.getImageId() + "/" + servicesModel.getImage()).into(holder.imageView);
        }else {
            holder.defaultImage.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);
        }
      /*  Bitmap bitmap=imageLoader.DownloadImageFromPath(accomodationModel.getImage());
        holder.imageView.setImageBitmap(bitmap);*/
        holder.Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,ServicesWebView.class);
                intent.putExtra("Url",servicesModel.getAccomd_location());
                context.startActivity(intent);
            }
        });
        holder.Gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //      String strUri = "http://maps.google.com/maps?q=loc:" + accomodationModel.getAccomd_gps_lat() + "," + accomodationModel.getAccomd_gps_lng()+ " (" + "Label which you want" + ")";
                Intent intent = new Intent(context, ServicesMapActivity.class);
                intent.putExtra("lat",servicesModel.getAccomd_gps_lat());
                intent.putExtra("lng", servicesModel.getAccomd_gps_lng());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return servicesModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView Name, Address, website, phone_number, type_accomodation, Gps;
        ImageView imageView,defaultImage;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.name);
            Address = (TextView) view.findViewById(R.id.address);
            website = (TextView) view.findViewById(R.id.web_link);
            phone_number = (TextView) view.findViewById(R.id.phone_number);
            Gps = (TextView) view.findViewById(R.id.gps_type);
            imageView = (ImageView) view.findViewById(R.id.image);
            defaultImage = (ImageView) view.findViewById(R.id.image1);
        }

    }
}


