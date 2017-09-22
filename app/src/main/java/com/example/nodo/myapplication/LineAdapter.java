package com.example.nodo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nodo on 19/09/17.
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.LineHolder> {

    private final List<UserModel> mUsers;
    OnClickListener onclicklistener;


    public interface OnClickListener{
        void onUserClick(UserModel user);

        void onMoreClick (int position,UserModel user);

        void onDeleteClick (int position, UserModel user);
    }


    public LineAdapter(List<UserModel> users, OnClickListener onclicklistener) {

        mUsers = users;
        this.onclicklistener = onclicklistener;
    }



    @Override
    public LineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_line_view, parent, false));
    }

    @Override
    public void onBindViewHolder(LineHolder holder, final int position) {

        final UserModel userModel = mUsers.get(position);

        holder.title.setText(userModel.name);
        holder.title2.setText(userModel.description);


        holder.moreButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onclicklistener.onUserClick(userModel);
            }
        });



        holder.addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onclicklistener.onMoreClick(position,userModel);
            }
        });



        holder.deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onclicklistener.onDeleteClick(position,userModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }




    class LineHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.main_line_title) TextView title;
        @BindView(R.id.main_line_title2) TextView title2;
        @BindView(R.id.main_line_more) Button moreButton;
        @BindView(R.id.main_line_delete) Button deleteButton;
        @BindView(R.id.main_line_add) Button addButton;


        LineHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
    }


}