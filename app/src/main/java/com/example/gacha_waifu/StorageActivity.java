package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gacha_waifu.databinding.ActivityStorageBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StorageActivity extends AppCompatActivity {
    private ActivityStorageBinding binding;
    private Preferences preferences;
    StorageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStorageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = new Preferences(this);
        String jsonData = preferences.getImageList();
        if(!jsonData.equals("")) {
            Type type = new TypeToken<ArrayList<String>>(){}.getType();
            ArrayList<String> datas = new Gson().fromJson(jsonData, type);
            adapter = new StorageAdapter(datas);
        }

        binding.rvImage.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvImage.setAdapter(adapter);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StorageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}