package com.example.fyp_robot_dog_androidx.Api_And_Function;

public class User {

    private String userid;
    private String name;
    private String email;
    private String password;
    private String phone;


    public User(){
        userid="";
        name="";
        email="";
        password="";
        phone="";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserid() {
        return userid;
    }
}
