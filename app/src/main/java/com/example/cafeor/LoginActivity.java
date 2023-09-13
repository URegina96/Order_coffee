package com.example.cafeor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;


public class LoginActivity extends AppCompatActivity {
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Hi, I love coffe and you?"); //добавила в эту строку, так как в xml не реагирует
        toolbar.setLogo(R.drawable.baseline_coffee_maker_24);
        toolbar.setTitleTextColor(0xFFFFFFFF);//добавила в эту строку, так как в xml не реагирует

    }
}