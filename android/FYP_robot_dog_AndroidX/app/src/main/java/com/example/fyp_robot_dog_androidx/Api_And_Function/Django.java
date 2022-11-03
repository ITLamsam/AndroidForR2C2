package com.example.fyp_robot_dog_androidx.Api_And_Function;

import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class Django {
    private OkHttpClient server;
    //    private FormBody formBody;
    private Request request;


    private String DjangoExceptionString="";
    private Exception DjangoException;
    private String CurrHttpRequestMethod;

    //private String ResponseDetail="";
    private String ResponseBody="";

    public Django(){
        this.server = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
    }

    public void SetCurrHttpRequestMethod(String CurrHttpRequestMethod){
        this.CurrHttpRequestMethod=CurrHttpRequestMethod;
    }

    public String GetCurrHttpRequestMethod(){
        return CurrHttpRequestMethod;
    }

    public void SetDjangoExceptionString(String DjangoExceptionString){
        this.DjangoExceptionString=DjangoExceptionString;
    }
    public void SetDjangoExceptionString(Exception DjangoException){
        this.DjangoExceptionString=DjangoException.getMessage();
    }
    public String GetDjangoExceptionString(){
        if(DjangoExceptionString!="" || DjangoExceptionString!=null){
            return DjangoExceptionString;
        }
        return null;
    }

    public void SetDjangoException(Exception DjangoException){
        SetDjangoExceptionString(DjangoException);
        this.DjangoException=DjangoException;
    }

    public Exception GetDjangoException(){
        return DjangoException;
    }
//    public void SetResponseDetail(String ResponseDetail){
//        this.ResponseDetail=ResponseDetail;
//
//    }
//
//    public String GetResponseDetail(){
//        return this.ResponseDetail;
//    }

    public void SetResponseBody(String ResponseBody){
        this.ResponseBody=ResponseBody;
    }

    public String GetResponseBody(){
        return ResponseBody;
    }


    public void sendPOST(FormBody formBody) {

        request = new Request.Builder()
                .url("http://sshop.tplinkdns.com:8011/api/")
                .post(formBody)
                .build();

        Call call = server.newCall(request);
        try {
            Response response = call.execute();
            SetCurrHttpRequestMethod("POST");
            SetResponseBody(response.body().string());
        }catch (Exception e){
            SetDjangoException(e);
            SetDjangoExceptionString(e.getMessage());
        }
    }

    public void sendPOST(FormBody formBody,String url) {

        request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = server.newCall(request);
        try {
            Response response = call.execute();
            SetCurrHttpRequestMethod("POST");
            SetResponseBody(response.body().string());
        }catch (Exception e){
            SetDjangoException(e);
            SetDjangoExceptionString(e.getMessage());
        }
    }


    public void sendGET() {

        request = new Request.Builder()
                .url("http://sshop.tplinkdns.com:8011/api/")
//                .addHeader("","")//If the API need header la
                .build();

        Call call = server.newCall(request);
        try {
            Response response = call.execute();
            SetCurrHttpRequestMethod("GET");
            SetResponseBody(response.body().string());
        }catch (Exception e){
            SetDjangoException(e);
            SetDjangoExceptionString(e.getMessage());
        }

//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                SetDjangoException(e);
//                  SetDjangoExceptionString(e.getMessage());
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                String body=response.body().string();
//                SetResponseBody(body);
//                //Log.d("response.body().string()",response.body().string());
//            }
//
//        });
    }

    public void sendGET(String Gurl) {


//        String Gurl=   HttpUrl.parse("http://sshop.tplinkdns.com:8011/api/user/").newBuilder()
//                .addQueryParameter("key", "US001")
//                .addQueryParameter("columns", "email, name, phone")
//                .build().toString();
        request = new Request.Builder()
                .url(Gurl)
//                .addHeader("","")//If the API need header la
                .build();

        Call call = server.newCall(request);
        try {
            Response response = call.execute();
            SetCurrHttpRequestMethod("GET");
            SetResponseBody(response.body().string());
        }catch (Exception e){
            SetDjangoException(e);
            SetDjangoExceptionString(e.getMessage());
        }

//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                SetDjangoException(e);
//                  SetDjangoExceptionString(e.getMessage());
//
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                String body=response.body().string();
//                SetResponseBody(body);
//                //Log.d("response.body().string()",response.body().string());
//            }
//
//        });
    }


}

