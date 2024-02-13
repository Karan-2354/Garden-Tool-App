package com.example.plant_app;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;
    List<photo> photo;

    public Myadapter(Context context, List<Item> items, List<com.example.plant_app.photo> photo) {
        this.context = context;
        this.items = items;
        this.photo = photo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Item temp=items.get(position);
        final photo temp1=photo.get(position);

        holder.imageView.setImageResource(photo.get(position).getPhoto());
        holder.nameView.setText(items.get(position).getName());
        holder.priceView.setText(items.get(position).getPrice());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,GallaryActivity.class);
                intent.putExtra("imagename",temp1.getPhoto());
                intent.putExtra("id",temp.getId());
                intent.putExtra("name",temp.getName());
                intent.putExtra("price",temp.getPrice());
                intent.putExtra("desc",temp.getDesc());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
