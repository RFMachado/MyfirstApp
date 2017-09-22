package com.example.nodo.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by nodo on 19/09/17.
 */

public class UserModel implements Serializable {

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public  String description;

}
