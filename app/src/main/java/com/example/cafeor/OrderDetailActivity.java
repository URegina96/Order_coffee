package com.example.cafeor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView  textViewOrder; //сслыка на текст
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        textViewOrder =findViewById(R.id.textViewOrder); //присваиваем занчение

        Intent intent = getIntent(); //получаем  заказ из пред-ей активности
        if (intent.hasExtra("order")){ //содержит ли intent наш ключ
            String order =intent.getStringExtra("order");
            textViewOrder.setText(order);
        }else { //если нет, то отправляем на главную
            Intent backToLogin = new Intent(this, LoginActivity.class);
            startActivity(backToLogin);
        }
    }
}