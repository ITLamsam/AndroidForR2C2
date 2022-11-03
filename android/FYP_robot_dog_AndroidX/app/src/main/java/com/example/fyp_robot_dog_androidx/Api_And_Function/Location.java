package com.example.fyp_robot_dog_androidx.Api_And_Function;

public class Location {
    private String LocationID;
    private String name;
    private String x;
    private String y;

    public Location(String LocationID,String name,String x,String y){
        this.LocationID=LocationID;
        this.name=name;
        this.x=x;
        this.y=y;
    }

    public Location(){
        this.LocationID="";
        this.name="";
        this.x="";
        this.y="";
    }
}
