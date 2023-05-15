package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gacha_waifu.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        System.out.println("Ini Tes Push dari orang lain");
        System.out.println("Ini Tes Push dari orang lain");
        System.out.println("Ini Tes Push dari orang lain");
        System.out.println("Ini Tes Push dari orang lain");
        System.out.println("Ini Tes Push dari orang lain");
        System.out.println("Ini Tes Push dari orang lain");

        preferences = new Preferences(this);

        binding.btnWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataPicture();
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

                        Toast.makeText(MainActivity.this, imageUrl, Toast.LENGTH_SHORT).show();

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
}