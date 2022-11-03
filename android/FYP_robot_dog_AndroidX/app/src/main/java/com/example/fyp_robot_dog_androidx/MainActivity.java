package com.example.fyp_robot_dog_androidx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fyp_robot_dog_androidx.Api_And_Function.Cache;
import com.example.fyp_robot_dog_androidx.Api_And_Function.CustomAlert;
import com.example.fyp_robot_dog_androidx.Api_And_Function.Django;
import com.example.fyp_robot_dog_androidx.Api_And_Function.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;

public class MainActivity extends AppCompatActivity{
    Button BtnSignUp;
    EditText LUserEmail,LUserPassword;
    Django UserDetails=new Django();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        //test();
        LUserEmail = findViewById(R.id.LUserEmail);
        LUserPassword=findViewById(R.id.LUserPassword);




    }

//    private void test(){
//        boolean x = CustomAlert.alertChoiceMessage(this, "123", "123", "123", "123");
//        if(x)
//            CustomAlert.alertMessage(this, "ok", "ok");
//        else
//            CustomAlert.alertMessage(this, "false", "false");
//    }

    public void SignUp(View v){
        Intent SignUp = new Intent(MainActivity.this, SignUp.class);
        startActivity(SignUp);
    }

    public void Login(View v){
        //TBC - Login connect database
//        String Gurl=   HttpUrl.parse("http://sshop.tplinkdns.com:8011/user/login").newBuilder()
//                        .addQueryParameter("email", LUserEmail.getText().toString())
//                        .addQueryParameter("password", LUserPassword.getText().toString())
//                        .build().toString();

        if(LUserEmail.getText().toString().equals("") || LUserPassword.getText().toString().equals("")){
            CustomAlert.alertMessage(this, "Error Message", "Please input Email and Password!");

        }else{

            new Thread(new Runnable(){
                @Override
                public void run() {
                    try{
                        FormBody body = new FormBody.Builder()
                                .add("email", LUserEmail.getText().toString())
                                .add("password", LUserPassword.getText().toString())
                                .build();
                        UserDetails.sendPOST(body,"http://sshop.tplinkdns.com:8011/user/login/");


                        if(UserDetails.GetResponseBody().equals("User is not found")){
                            Looper.prepare();
                            ErrorMessage("User is not found!");
                            Looper.loop();
                            Log.d("","User is not found!");

//                            CustomAlert.alertMessage(MainActivity.this, "Error Message", "User is not found!");
                        }else if(UserDetails.GetResponseBody().equals("Password is not matched") ){
                            Looper.prepare();
                            ErrorMessage("Password is not matched!");
                            Looper.loop();
                            Log.d("","Password is not matched!");
//                            CustomAlert.alertMessage(MainActivity.this,"Error Message","Password is not matched");
                        }else{

                            Gson gson = new Gson();

                            JSONObject User = new JSONObject(UserDetails.GetResponseBody());
                            JSONObject Userdetail = new JSONObject(User.getJSONObject(User.keys().next()).toString());

//                            Log.d("asfasfasf",User.toString());
//                            Log.d("asfasfasf",User.keys().next());
//                            Log.d("asfasfasf",Userdetail.toString());

                            Cache.user = gson.fromJson(Userdetail.toString(), User.class);
                            Cache.user.setUserid(User.keys().next());
//                            Log.d("asfasfasf",Cache.user.getUserid());
//                            Log.d("asfasfasf",Cache.user.getName());

                            Intent Home = new Intent(MainActivity.this, Home.class);
                            startActivity(Home);
                        }


                    }catch(Exception e){
                        UserDetails.SetDjangoException(e);
                        Log.d("DjangoError:",UserDetails.GetDjangoExceptionString());
//                        Log.d("BBBBBBBBBBBBBBB",UserDetails.GetDjangoException().getMessage());
                    }

                }

            }).start();


        }



    }

    void ErrorMessage(String errorMsg) {
        CustomAlert.alertMessage(MainActivity.this,"Error Message",errorMsg);
    }
}



    class getUserTask extends AsyncTask<String, Integer, String> {
    //onPreExecute方法用于在执行后台任务前做一些UI操作
    @Override
    protected void onPreExecute() {

    }

    //doInBackground方法内部执行后台任务,不可在此方法内修改UI
    @Override
    protected String doInBackground(String... params) {

        return null;
    }
}
