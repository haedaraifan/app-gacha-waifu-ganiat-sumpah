package com.example.gacha_waifu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StorageAdapter extends RecyclerView.Adapter<StorageAdapter.ViewHolder> {

    private ArrayList<String> localDataSet;

    public StorageAdapter(ArrayList<String> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public StorageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivPoster;

        public ViewHolder(@NonNull View view) {
            super(view);

            ivPoster = view.findViewById(R.id.iv_item);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String imageUrl = localDataSet.get(position);

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.onboarding_3)
                .error(R.drawable.not_found)
                .into(viewHolder.ivPoster);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
