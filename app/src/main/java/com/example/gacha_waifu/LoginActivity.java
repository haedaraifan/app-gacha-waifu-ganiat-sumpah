package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gacha_waifu.database.AppDatabase;
import com.example.gacha_waifu.database.User;
import com.example.gacha_waifu.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String username, password;
    private AppDatabase db;
    private ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "db_user").allowMainThreadQueries().build();

        users.addAll(db.userDao().getAllUser());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = binding.edUsername.getText().toString();
                password = binding.edPassword.getText().toString();

                if(formIsEmpty()) {
                    toastMessage("Masih ada yg kosong WOI !!!");
                } else {
                    loginProcess();
                }
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean formIsEmpty() {
        return username.isEmpty() || password.isEmpty();
    }

    private void loginProcess() {

        if(username.equals("admin") && password.equals("admin")) {
            goToMainActivity();
        }
        else if(users.size() != 0) {

            for(int i = 0; i < users.size(); i++) {

                String userOnDB = users.get(i).username;
                String passwordOnDB = users.get(i).password;

                if(username.equals(userOnDB) && password.equals(passwordOnDB)) {
                    goToMainActivity();
                }
            }
        }
        else {
            toastMessage("Incorrect");
        }
    }

    private void toastMessage(String value) {
        Toast.makeText(LoginActivity.this, value, Toast.LENGTH_SHORT).show();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}