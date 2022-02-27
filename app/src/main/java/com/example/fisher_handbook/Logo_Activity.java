package com.example.fisher_handbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class Logo_Activity extends Activity {
    //Создаем переменные для анимации
    private Animation logoAnim, buttonLogoAnim;
    private Button butAnim;
    private ImageView logoImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        init();
        startMainMenu();
    }

    private void init() {
        //Загружаем анимации в переменные
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_anim);
        buttonLogoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_anim);

        logoImage = findViewById(R.id.LogoView);
        butAnim = findViewById(R.id.buttonAnim);

        //Запускаем анимацию
        logoImage.startAnimation(logoAnim);
        butAnim.startAnimation(buttonLogoAnim);
    }


    public void onClickStart(View view) {
        Intent intent = new Intent(Logo_Activity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private void startMainMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Logo_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        }).start();
    }
}
