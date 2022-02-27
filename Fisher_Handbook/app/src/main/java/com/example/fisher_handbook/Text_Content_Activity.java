package com.example.fisher_handbook;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

public class Text_Content_Activity extends AppCompatActivity {

    private ActionBar actionBar;
    private TextView text_content;
    //private ImageView image_content;
    private SubsamplingScaleImageView image_content;
    private Typeface raleway_thin;
    private SharedPreferences default_pref;
    private int category = 0;
    private int position = 0;
    private final int[] array_fish = {R.string.menu_fishes_1, R.string.menu_fishes_2, R.string.menu_fishes_3, R.string.menu_fishes_4, R.string.menu_fishes_5};
    private final int[] array_bait = {R.string.menu_bait_1, R.string.menu_bait_2, R.string.menu_bait_3, R.string.menu_bait_4, R.string.menu_bait_5};
    private final int[] array_tackle = {R.string.menu_tackle_1, R.string.menu_tackle_2, R.string.menu_tackle_3, R.string.menu_tackle_4};
    private final int[] array_food = {R.string.menu_food_1, R.string.menu_food_2, R.string.menu_food_3};
    private final int[] array_history = {R.string.other_history_1, R.string.other_history_2, R.string.other_history_3, R.string.other_history_4};
    private final int[] array_advice = {R.string.other_advice_1, R.string.other_advice_2, R.string.other_advice_3, R.string.other_advice_4};

    private final int[] array_image_fishes = {R.drawable.carp, R.drawable.chuka, R.drawable.som, R.drawable.osetr, R.drawable.nalim};

    //private String[] array_title_fishes = {"Карп", "Щука", "Сом", "Осётр", "Налим"}; - вариант с использованием string_array

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);
        init();
        receivedIntent();
        TextView textView = findViewById(R.id.text_main_content);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void init() {
        default_pref = PreferenceManager.getDefaultSharedPreferences(this);
        text_content = findViewById(R.id.text_main_content);
        image_content = findViewById(R.id.imageFishes);
        raleway_thin = Typeface.createFromAsset(this.getAssets(), "fonts/Raleway-Regular.ttf");
        text_content.setTypeface(raleway_thin);
        actionBar = getSupportActionBar();

        String text = default_pref.getString("main_text_size", "Средний");
        if (text != null) {
            switch (text) {
                case "Большой текст":
                    text_content.setTextSize(22);
                    break;
                case "Средний текст":
                    text_content.setTextSize(18);
                    break;
                case "Маленький текст":
                    text_content.setTextSize(14);
                    break;
            }
        }
    }

    private void receivedIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            category = intent.getIntExtra("category", 0);
            position = intent.getIntExtra("position", 0);
        }

        switch (category) {
            case 0:
                image_content.setImage(ImageSource.resource(array_image_fishes[position]));
                text_content.setText(array_fish[position]);
                actionBar.setTitle(getResources().getStringArray(R.array.fishes_array)[position]);

                //actionBar.setTitle(array_title_fishes[position]); - вариант с использованием string_array. наверху, под названием array_title_fishes
                break;
            case 1:
                text_content.setText(array_bait[position]);
                break;
            case 2:
                text_content.setText(array_tackle[position]);
                break;
            case 3:
                text_content.setText(array_food[position]);
                break;
            case 4:
                text_content.setText(array_history[position]);
                break;
            case 5:
                text_content.setText(array_advice[position]);
                break;
        }
    }
}