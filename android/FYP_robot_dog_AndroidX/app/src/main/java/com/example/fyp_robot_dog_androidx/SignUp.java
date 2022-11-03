package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    private EditText EdPassword, EdConfirmPassword, EdUserName, EdEmail, EdPhone;
    private ImageButton hidePasswordBtn1, hidePasswordBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        initEleID();
    }

    private void initEleID(){
        EdPassword = findViewById(R.id.EdPassword);
        EdConfirmPassword = findViewById(R.id.EdConfirmPassword);
        EdUserName = findViewById(R.id.EdUserName);
        EdEmail = findViewById(R.id.EdEmail);
        EdPhone = findViewById(R.id.EdPhone);
        hidePasswordBtn1 = findViewById(R.id.hidePassword1);
        hidePasswordBtn2 = findViewById(R.id.hidePassword2);
        hidePasswordBtn1.setOnClickListener(this);
        hidePasswordBtn2.setOnClickListener(this);
    }

    public void backPreviousPage(View v){
//        onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void Register(View v){
        if(ColumnIsNull())
            CustomAlert.alertMessage(SignUp.this, "Alert Message", "The column can not be null");
        else if(!EdPassword.getText().toString().equals(EdConfirmPassword.getText().toString()))
            CustomAlert.alertMessage(SignUp.this,"Alert Message", "The passwords does not match");
        else{
            Intent intent = new Intent(this, Email_Versification.class);
            startActivity(intent);
        }
    }

    private boolean ColumnIsNull(){
        return EdUserName.getText().toString().isEmpty() || EdEmail.getText().toString().isEmpty() || EdPhone.getText().toString().isEmpty() ||
                EdPassword.getText().toString().isEmpty() || EdConfirmPassword.getText().toString().isEmpty();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.hidePassword1:
                if(EdPassword.getTransformationMethod()==null)
                    EdPassword.setTransformationMethod(new PasswordTransformationMethod());
                else
                    EdPassword.setTransformationMethod(null);
                break;
            case R.id.hidePassword2:
                if(EdConfirmPassword.getTransformationMethod()==null)
                    EdConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
                else
                    EdConfirmPassword.setTransformationMethod(null);
                break;
        }
    }
}