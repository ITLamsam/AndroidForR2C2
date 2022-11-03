package com.example.fyp_robot_dog_androidx.Api_And_Function;

import java.util.ArrayList;

public class Cache {

    public static User user= new User();
    public static ArrayList<Location> location= new ArrayList<Location>();

    public ArrayList getAllLocation(){



        return location;
    }

}
