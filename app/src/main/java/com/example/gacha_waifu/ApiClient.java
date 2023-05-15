package com.example.gacha_waifu;

import retrofit2.Retrofit;

public class ApiClient {
    public static Retrofit retrofit;
    public static final String BASE_URL = "https://api.waifu.pics/";

    public static Retrofit getRetrofitInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
