package com.example.nodo.myapplication;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by nodo on 22/09/17.
 */

public interface APIInterface {

    @GET("users")
    Call<List<UserModel>> getUsers();

    @POST("users")
    Call<UserModel> createUser(@Body ArrayList<UserModel> user);

    @GET("user")
    Call<UserModel> getUser();

}
