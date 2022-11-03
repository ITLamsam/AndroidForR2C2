package com.example.fyp_robot_dog_androidx.Api_And_Function.Home_Recyclerview_Item_Config;

public class Notification_Item {
    private String title;
    private String content;

    public Notification_Item(String title, String content){
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
