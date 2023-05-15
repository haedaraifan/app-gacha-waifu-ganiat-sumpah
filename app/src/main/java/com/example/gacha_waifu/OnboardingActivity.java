package com.example.gacha_waifu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class OnboardingActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        preferences = new Preferences(this);
        if(preferences.getSessionLogin()) {
            Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
            startActivity(intent);
        }

        fragmentManager = getSupportFragmentManager();
        PaperOnboardingFragment paperOnboardingFragment =PaperOnboardingFragment.newInstance(getDataForOnboarding());
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment);
        fragmentTransaction.commit();

        paperOnboardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                Intent intent = new Intent(OnboardingActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        String[] onboardingTitle = getResources().getStringArray(R.array.onboarding_title);
        String[] onboardingDescription = getResources().getStringArray(R.array.onboarding_description);

        PaperOnboardingPage page1 = new PaperOnboardingPage(onboardingTitle[0], onboardingDescription[0], Color.parseColor("#ffffff"), R.drawable.onboarding_1, R.drawable.ic_4k);
        PaperOnboardingPage page2 = new PaperOnboardingPage(onboardingTitle[1], onboardingDescription[1], Color.parseColor("#ffffff"), R.drawable.onboarding_2, R.drawable.ic_hd);
        PaperOnboardingPage page3 = new PaperOnboardingPage(onboardingTitle[2], onboardingDescription[2], Color.parseColor("#ffffff"), R.drawable.onboarding_3, R.drawable.ic_4g);

        elements.add(page1);
        elements.add(page2);
        elements.add(page3);

        return elements;
    }
}