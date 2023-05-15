package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gacha_waifu.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Preferences preferences;
    private ArrayList<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = new Preferences(this);
        String newJsonData = preferences.getImageList();
        if(!newJsonData.equals("")) {
            Type type = new TypeToken<ArrayList<String>>(){}.getType();
            ArrayList<String> newDatas = new Gson().fromJson(newJsonData, type);

            datas.addAll(newDatas);

            String imageUrl = datas.get(getLastIndex());

            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.onboarding_3)
                    .error(R.drawable.not_found)
                    .into(binding.ivResult);
        } else {
            binding.ivResult.setImageResource(R.drawable.onboarding_3);
        }

        binding.btnWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataPicture();
            }
        });

        binding.btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updatePreferences();

                Intent intent = new Intent(MainActivity.this, StorageActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Logout ...", Toast.LENGTH_SHORT).show();
                preferences.setSessionLogin(false);

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDataPicture() {
        ApiService service = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<ResponseBody> call = service.getImage();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String res;
                try {
                    res = response.body().string();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if(res != null) {
                    try {
                        JSONObject jsonResponse = new JSONObject(res);
//                        JSONArray images = jsonResponse.getJSONArray("images");
//                        JSONObject data  = images.getJSONObject(0);
//                        JSONObject artist = data.getJSONObject("artist");
//                        String name = artist.getString("name");
                        String imageUrl = jsonResponse.getString("url");
                        datas.add(imageUrl);

                        Toast.makeText(MainActivity.this, imageUrl, Toast.LENGTH_SHORT).show();

                        updatePreferences();
                        Picasso.get()
                                .load(imageUrl)
                                .placeholder(R.drawable.onboarding_3)
                                .error(R.drawable.not_found)
                                .into(binding.ivResult);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong... Please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getLastIndex() {
        return datas.size() - 1;
    }

    private void updatePreferences() {
        String jsonData = new Gson().toJson(datas);
        preferences.setImageList(jsonData);
    }
}