package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gacha_waifu.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private String username, password;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        preferences = new Preferences(this);
        if(preferences.getSessionLogin()) {
            goToMainActivity();
        }

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
    }

    private boolean formIsEmpty() {
        return username.isEmpty() || password.isEmpty();
    }

    private void loginProcess() {

        if(username.equals("admin") && password.equals("admin")) {
            preferences.setSessionLogin(true);
            goToMainActivity();
        } else {
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