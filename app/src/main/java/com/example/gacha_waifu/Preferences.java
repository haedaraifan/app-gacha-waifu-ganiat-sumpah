package com.example.gacha_waifu;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

public class Preferences {
    SharedPreferences pref;
    Editor editor;
    private final String sessionLogin = "session_login";
    private final String userList = "user_list";

    public Preferences(Context context) {
        pref = context.getSharedPreferences("myPref", context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setSessionLogin(boolean value) {
        editor.putBoolean(sessionLogin, value);
        editor.commit();
    }

    public boolean getSessionLogin() {
        return pref.getBoolean(sessionLogin, false);
    }
}
