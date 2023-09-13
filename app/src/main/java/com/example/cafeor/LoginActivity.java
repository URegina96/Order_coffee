package com.example.cafeor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextName; //переменные для доступа к графическим элементам
    private EditText editTextPassword;//переменные для доступа к графическим элементам
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

        //присваиваем им значения
        editTextName = findViewById(R.id.editTextTextName);
        editTextPassword =findViewById(R.id.editTextPassword);
    }
    public void onClickCreateOrder(View view){
        //получаем текст введенный пользователем, преобразовывая сразу в строку
        String name=editTextName.getText().toString().trim();
        //.trim() - избавляемся от лишних пробелов (до и после допустим)
        String password =editTextPassword.getText().toString().trim();
        //теперь запускаем новую активность только когда name и password содержат  какие-либо символы
        if (!name.isEmpty()&&!password.isEmpty()){
            Intent intent =new Intent(this,CreateOrderActivity.class); //вызываем новую активность

            // вкалдываем данные в intent
            intent.putExtra("name",name);
            intent.putExtra("password",password);

            startActivity(intent); //запускаем новую активнсть
        }else{ //создаем всплывающее окно, с которым не может взаимодействовать пользователь (в том случае, если какое-то поле не заполнено)
            Toast.makeText(this,R.string.warning_fill_fields, Toast.LENGTH_SHORT).show();
        }
    }

}