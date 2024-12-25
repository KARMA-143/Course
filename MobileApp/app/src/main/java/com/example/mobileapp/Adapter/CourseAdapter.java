package com.example.mobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileapp.Domain.CourseDomain;
import com.example.mobileapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    ArrayList<CourseDomain> items;
    DecimalFormat formatter;
    Context context;

    public CourseAdapter(ArrayList<CourseDomain> items) {
        this.items = items;
        formatter=new DecimalFormat("###,###,###,###.##");
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_course,parent,false);
        context=parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        holder.titleText.setText(items.get(position).getTitle());
        holder.ownerText.setText(items.get(position).getOwner());
        holder.priceText.setText("$"+formatter.format(items.get(position).getPrice()));
        holder.starText.setText(formatter.format(items.get(position).getStar()));
        Glide.with(holder.itemView.getContext())
                .load(items.get(position).getCourseCover())
                .into(holder.courseCover);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView titleText, ownerText, priceText, starText;
        ImageView courseCover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText=itemView.findViewById(R.id.titleText);
            ownerText=itemView.findViewById(R.id.ownerText);
            priceText=itemView.findViewById(R.id.priceText);
            starText=itemView.findViewById(R.id.starText);
            courseCover=itemView.findViewById(R.id.courseCover);
        }
    }
}
