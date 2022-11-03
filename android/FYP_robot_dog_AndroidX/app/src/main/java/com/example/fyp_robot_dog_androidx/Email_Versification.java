package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;

public class Email_Versification extends AppCompatActivity {
    private EditText code1, code2, code3, code4;
    private boolean IsRunWinFocusAction;
    private EditText[] codeArray = new EditText[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_versification);
        getSupportActionBar().hide();
        initId();
        EditTextEvent();
    }

    private void EditTextEvent(){
        for (int x = 0; x < codeArray.length; x++) {
            // focus on editbox, change the radius color
            EditBoxFocusEvent(codeArray[x]);
            // type a word on edittext to go to another edittext
            if(x != codeArray.length -1) EditBoxTextChangeEvent(codeArray[x], codeArray[x + 1]);
        }
    }

    private void EditBoxFocusEvent(EditText self){
        self.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    self.setBackgroundResource(R.drawable.focus_email_verification_box);
                } else {
                    self.setBackgroundResource(R.drawable.email_verification_box);
                }
            }
        });
    }

    private void EditBoxTextChangeEvent(EditText self, EditText next){
        self.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if(self.getText().length()!=0)
                    next.requestFocus();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(!IsRunWinFocusAction) {
            code1.setMinWidth(code1.getWidth());
            code2.setMinWidth(code2.getWidth());
            code3.setMinWidth(code3.getWidth());
            code4.setMinWidth(code4.getWidth());
            IsRunWinFocusAction = true;
        }
    }

    private void initId(){
        code1 = findViewById(R.id.code1);
        code2 = findViewById(R.id.code2);
        code3 = findViewById(R.id.code3);
        code4 = findViewById(R.id.code4);
        codeArray = new EditText[]{code1, code2, code3, code4};
    }

    public void backPreviousPage(View view){
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}