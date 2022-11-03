package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home_Place_Item_success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_place_item_success);
        getSupportActionBar().hide();
    }

    public void back(View view){
        finish();
    }

    public void BackToHome(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}