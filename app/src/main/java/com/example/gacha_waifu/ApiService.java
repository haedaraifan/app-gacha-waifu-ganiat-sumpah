package com.example.gacha_waifu;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("sfw/blush")
    Call<ResponseBody> getImage();
}
