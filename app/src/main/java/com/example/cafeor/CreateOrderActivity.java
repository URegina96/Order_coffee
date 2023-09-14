package com.example.cafeor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    //открываем доступ к переменным
    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffe;

    //создаем строковую переменную, которая будет хранить название выбранного напитка
    private String drink;
    //для имени и пароля
    private String name;
    private String password;

    private StringBuilder builderAdditions;  //объект для формирования списка добавок
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        //первый текст hello формируется в зависимости от имени пользователя, это им получаем из интента (и пароль сразу получим)
        Intent intent = getIntent();
        //если что-то пошло не так и наш интент не ссодержит значений и что бы не было ошибок, делаем проверку
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else { //тогда дадим значение по умолчанию
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }
        //теперь формируем строку приветствия

        //делаем по умолчанию
        drink = getString(R.string.tea);

        //инициализируем переменные
        textViewHello = findViewById(R.id.textViewHello);
        String hello = String.format(getString(R.string.hello_user), name); //теперь формируем строку приветствия
        textViewHello.setText(hello);

        textViewAdditions = findViewById(R.id.textViewAdditions);
        String additions =String.format(getString(R.string.additions),drink);
        textViewAdditions.setText(additions);

        checkBoxMilk = findViewById(R.id.checkboxMilk);
        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);
        spinnerCoffe = findViewById(R.id.spinnerCoffe);
        spinnerTea = findViewById(R.id.spinnerTea);

        builderAdditions = new StringBuilder();
    }

    public void onClickChangeDrink(View view) { //меняем действия, при выборе другого который не по умолчанию) напитка
        RadioButton button = (RadioButton) view; //получили нажатую кнопку
        int id = button.getId(); //получили ее id
        if (id == R.id.radioButtonTea) {
            drink = getString(R.string.tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffe.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);


        } else if (id == R.id.radioButtonCoffee){
            drink =getString((R.string.coffe));
            spinnerTea.setVisibility(View.INVISIBLE);
            spinnerCoffe.setVisibility(View.VISIBLE);
            checkBoxLemon.setVisibility(View.INVISIBLE);
        }
        //теперь формируем строку (текст)  выбора добавок
}

    public void onClicksSendOrder(View view) {
        builderAdditions.setLength(0); //очищаем все отметки gпо умолчанию
        if (checkBoxMilk.isChecked()) {
            builderAdditions.append(getString(R.string.milk)).append(" ");
        }
        if (checkBoxSugar.isChecked()) {
            builderAdditions.append(getString(R.string.sugar)).append(" ");
        }
        if (checkBoxLemon.isChecked()&& drink.equals(getString(R.string.tea))) {
            builderAdditions.append(getString(R.string.lemon)).append(" ");
        }
         String optionOfDrink = "";  //для получения вида напитка из spinner
            if(drink.equals(getString(R.string.tea))){
                optionOfDrink =spinnerTea.getSelectedItem().toString();
            }else {
                optionOfDrink =spinnerCoffe.getSelectedItem().toString();
            }
        String order = String.format(getString(R.string.order), name,password,drink,optionOfDrink);

            String additions; //строка для добавок
        if(builderAdditions.length()>0){
            additions = getString(R.string.need_additions) + builderAdditions.toString();
        }else {
            additions="";
        }

        //Строка полного запроса
        String fullOrder = order+additions;

        Intent intent=new Intent(this,OrderDetailActivity.class);
        intent.putExtra("order",fullOrder);
        startActivity(intent);
    }
}