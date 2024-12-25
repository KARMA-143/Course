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
import com.example.mobileapp.Domain.CategoryDomain;
import com.example.mobileapp.R;

import java.util.ArrayList;

public class CategoryListItemAdapter extends RecyclerView.Adapter<CategoryListItemAdapter.ViewHolder> {
    private ArrayList<CategoryDomain> items;
    private Context context;

    public CategoryListItemAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryListItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categorylistitem, parent, false);
        context = parent.getContext();
        return new CategoryListItemAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListItemAdapter.ViewHolder holder, int position) {
        CategoryDomain category = items.get(position);
        holder.titleText.setText(category.getName());
        Glide.with(holder.categoryImg.getContext())
                .load(category.getCategoryImg())
                .into(holder.categoryImg);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView categoryImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            categoryImg = itemView.findViewById(R.id.categoryImg);
        }
    }
}
