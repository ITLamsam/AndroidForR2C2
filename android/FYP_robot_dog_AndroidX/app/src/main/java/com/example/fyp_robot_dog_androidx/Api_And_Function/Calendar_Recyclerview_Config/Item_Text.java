package com.example.fyp_robot_dog_androidx.Api_And_Function.Calendar_Recyclerview_Config;

public class Item_Text {
    private String text;
    private String textColor;

    public Item_Text(String text, String textColor){
        this.text = text;
        this.textColor = textColor;
    }

    public String getTextColor(){
        return textColor;
    }

    public String getText(){return text; }
}
