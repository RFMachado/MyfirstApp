package com.example.nodo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.*;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LinearLayoutActivity extends AppCompatActivity implements LineAdapter.OnClickListener{


    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    private LineAdapter mAdapter;
    List<UserModel> users;
    APIInterface apiInterface ;
    // APICliente apiCliente;
    // Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        apiInterface = APICliente.conect();

        setupRecycler();
    }


    public void setupRecycler() {

        Call <List<UserModel>> call2 = apiInterface.getUsers();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        call2.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call2, Response<List<UserModel>> response) {
                sendAdapter(response); // Recebe servidor e envia para adpater
            }

            @Override
            public void onFailure(Call<List<UserModel>> call2, Throwable t) {
                Toast.makeText(LinearLayoutActivity.this,"FALHA",Toast.LENGTH_LONG).show();  //msg error
            }
        });

    }


    public void sendAdapter(Response<List<UserModel>> response){
        List<UserModel> users = response.body();
        LinearLayoutActivity.this.users = users;

        mAdapter = new LineAdapter(users,LinearLayoutActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onUserClick(UserModel user) {
        Intent intent = new Intent(this,DescriptionActivity.class);

        intent.putExtra("data",user);

        startActivity(intent);
    }

    @Override
    public void onMoreClick(int position,UserModel user) {
        users.add(user);
        mAdapter.notifyItemInserted(users.size()-1);

    }

    @Override
    public void onDeleteClick(int position,UserModel user) {
        users.remove(position);
        mAdapter.notifyItemRemoved(users.size());
        mAdapter.notifyItemRangeChanged(position,users.size());

    }


}
