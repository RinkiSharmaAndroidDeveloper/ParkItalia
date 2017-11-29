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
import com.parkitalia.android.activities.AccomodationMapActivity;
import com.parkitalia.android.activities.AccomodationModel;
import com.parkitalia.android.activities.AccomodationWebViewActivity;

import java.util.ArrayList;

/**
 * Created by Android-Dev2 on 5/30/2017.
 */

public class AccomodationAdapter extends RecyclerView.Adapter<AccomodationAdapter.MyViewHolder> {
    Context context;
    ImageLoader imageLoader =new ImageLoader();
    ArrayList<AccomodationModel> accomodationModels;
    public AccomodationAdapter(Context context,ArrayList<AccomodationModel> accomodationModels) {
        this.context =context;
        this.accomodationModels=accomodationModels;
    }


    @Override
    public AccomodationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_of_accomodation, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccomodationAdapter.MyViewHolder holder, int position) {
    final AccomodationModel accomodationModel = accomodationModels.get(position);
        holder.Name.setText(accomodationModel.getAccomd_name());
        String data1= String.valueOf(accomodationModel.getAccomd_gps_lat());
        String data2= String.valueOf(accomodationModel.getAccomd_gps_lng());
        holder.Address.setText(accomodationModel.getAccomd_wevsite());
        holder.website.setText(accomodationModel.getAccomd_location());
        holder.phone_number.setText(accomodationModel.getAccomd_phone());
        holder.type_accomodation.setText("Type :"+accomodationModel.getAccomd_type());
        holder.Gps.setText("Take Me There");
        if(accomodationModel.getImage()!=null)
        {
            holder.defaultImage.setVisibility(View.GONE);
            holder.imageView.setVisibility(View.VISIBLE);
            com.squareup.picasso.Picasso.with(context).
                    load("http://indotesting.com/parkme/uploads/accommodation/" + accomodationModel.getImageId() + "/" + accomodationModel.getImage()).resize(550, 450).into(holder.imageView);
       /* Picasso.with(context).load(new File("http://indotesting.com/parkme/uploads/accommodation/"+accomodationModel.getImageId()+"/"+accomodationModel.getImage())).resize(550,450)
        .placeholder(R.mipmap.ic_launcher)
        .into(holder.imageView);*/

        }else {
            holder.defaultImage.setVisibility(View.VISIBLE);
            holder.imageView.setVisibility(View.GONE);

        }

      /*  Bitmap bitmap=imageLoader.DownloadImageFromPath(accomodationModel.getImage());
        holder.imageView.setImageBitmap(bitmap);*/
        holder.Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, AccomodationWebViewActivity.class);
                intent.putExtra("Url",accomodationModel.getAccomd_location());
                context.startActivity(intent);
            }
        });
         holder.Gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          //      String strUri = "http://maps.google.com/maps?q=loc:" + accomodationModel.getAccomd_gps_lat() + "," + accomodationModel.getAccomd_gps_lng()+ " (" + "Label which you want" + ")";
                Intent intent = new Intent(context, AccomodationMapActivity.class);
                intent.putExtra("lat",accomodationModel.getAccomd_gps_lat());
                intent.putExtra("lng", accomodationModel.getAccomd_gps_lng());
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
        ImageView imageView,defaultImage;

        public MyViewHolder(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.name);
            Address = (TextView) view.findViewById(R.id.address);
            website = (TextView) view.findViewById(R.id.web_link);
            phone_number = (TextView) view.findViewById(R.id.phone_number);
            type_accomodation = (TextView) view.findViewById(R.id.accomodation_type);
            Gps = (TextView) view.findViewById(R.id.gps_type);
            imageView = (ImageView) view.findViewById(R.id.image);
            defaultImage = (ImageView) view.findViewById(R.id.image1);
        }

    }
}
