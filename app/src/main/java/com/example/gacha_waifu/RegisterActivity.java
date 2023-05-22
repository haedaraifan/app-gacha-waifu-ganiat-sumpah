package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gacha_waifu.database.AppDatabase;
import com.example.gacha_waifu.database.User;
import com.example.gacha_waifu.databinding.ActivityRegisterBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private String username, password;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db_user").allowMainThreadQueries().build();

        username = binding.edUsername.getText().toString();
        password = binding.edPassword.getText().toString();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.username = binding.edUsername.getText().toString();
                user.password = binding.edPassword.getText().toString();

                insertData(user);
                goToLoginActivity();
//                if(!formIsEmpty()) {
//                } else {
//                    toastMessage("Masih ada yg kosong WOI !!!");
//                }
            }
        });

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });
    }

    private void insertData(User user) {
        db.userDao().insert(user);
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void toastMessage(String value) {
        Toast.makeText(RegisterActivity.this, value, Toast.LENGTH_SHORT).show();
    }

    private boolean formIsEmpty() {
        return username.isEmpty() || password.isEmpty();
    }
}