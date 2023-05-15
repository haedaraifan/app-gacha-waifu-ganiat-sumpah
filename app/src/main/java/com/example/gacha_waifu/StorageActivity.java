package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.gacha_waifu.databinding.ActivityStorageBinding;

import java.util.ArrayList;

public class StorageActivity extends AppCompatActivity {
    private ActivityStorageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStorageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        ArrayList<String> images = getListImage();
//        StorageAdapter adapter = new StorageAdapter(images);
//        binding.rvImage.setLayoutManager(new GridLayoutManager(this, 2));
//        binding.rvImage.setAdapter(adapter);
    }

//    private ArrayList<String> getListImage() {
//
//    }
}