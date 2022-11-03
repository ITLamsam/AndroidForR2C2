package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;

import java.util.ArrayList;

public class New_Order_Select_Receiver extends AppCompatActivity implements View.OnClickListener{
    private TextView phone, name, email, receiverData;
    private String SelectDay, SelectTime;
    private AutoCompleteTextView autoCompleteTextView;

    private ArrayList<String> userName = new ArrayList<>();
    private ArrayList<String> userEmail = new ArrayList<>();
    private ArrayList<String> userPhone = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_order_select_receiver);
        init();

        loadDatabaseData();
        setUpAutoCompleteTextBox(userPhone);
    }

    private void init(){
        phone = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        receiverData = findViewById(R.id.receiverData);
        phone.setOnClickListener(this);
        name.setOnClickListener(this);
        email.setOnClickListener(this);
        autoCompleteTextView = findViewById(R.id.receiverData);

        SelectDay = getIntent().getStringExtra("SelectDate");
        SelectTime = getIntent().getStringExtra("SelectTime");

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                textChangeEvent();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }

    private void textChangeEvent(){
        //CustomAlert.alertMessage(this, "23", "23");
    }

    private void loadDatabaseData(){
        // TBC -- database textbox (tips)
        // autocomplete box
        userName.add("Amy");
        userName.add("Leo");
        userName.add("Maq");
        userName.add("Marry");

        userEmail.add("123@gmail.com");
        userEmail.add("3232@gmail.com");
        userEmail.add("dawd@yahoo.com");

        userPhone.add("123123");
        userPhone.add("42421");
        userPhone.add("343434");
    }

    private void setUpAutoCompleteTextBox(ArrayList<String> arrayList){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, arrayList);

       if(!adapter.isEmpty()) autoCompleteTextView.setAdapter(adapter);
    }

    public void Back(View view) {
        finish();
    }

    public void next(View view) {
        Intent intent = new Intent(this, New_Order_Select_Location_Item_type.class);
        intent.putExtra("SelectDate", SelectDay);
        intent.putExtra("SelectTime", SelectTime);
        Log.d("asfasf",receiverData.getText().toString());
        intent.putExtra("Receiver", receiverData.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        phone.setBackgroundResource(R.color.backgroundColor);
        name.setBackgroundResource(R.color.backgroundColor);
        email.setBackgroundResource(R.color.backgroundColor);

        switch (v.getId()){
            case R.id.phone:
                phone.setBackgroundResource(R.drawable.backgroundcolor_button_radius_white);
                setUpAutoCompleteTextBox(userPhone);
                break;
            case R.id.email:
                email.setBackgroundResource(R.drawable.backgroundcolor_button_radius_white);
                setUpAutoCompleteTextBox(userEmail);
                break;
            case R.id.name:
                name.setBackgroundResource(R.drawable.backgroundcolor_button_radius_white);
                setUpAutoCompleteTextBox(userName);
                break;
        }
    }
}