package com.example.nodo.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nodo on 22/09/17.
 */

public class APICliente {


    public static Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();



        return new Retrofit.Builder()
                .baseUrl("http://demo4878340.mockable.io/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static  APIInterface conect() {
        return getClient().create(APIInterface.class);
    }

}
