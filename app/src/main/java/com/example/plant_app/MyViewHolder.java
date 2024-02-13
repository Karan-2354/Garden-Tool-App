package com.example.plant_app;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView nameView;
    TextView priceView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=itemView.findViewById(R.id.image);
        nameView=itemView.findViewById(R.id.name);
        priceView=itemView.findViewById(R.id.price);

    }
}
