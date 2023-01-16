package com.example.YouOweMeProject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.Model.Friends;
import com.example.YouOweMeProject.R;
import com.example.YouOweMeProject.Welcome.LoginActivity;

import java.util.ArrayList;

public class MyFriendsListAdapter extends RecyclerView.Adapter<MyFriendsListAdapter.MyViewHolder> {
    Context context;
    ArrayList<Friend> list;
    SelectListener selectListener;

    public MyFriendsListAdapter(Context context, ArrayList<Friend> list, SelectListener selectListener) {
        this.context = context;
        this.list = list;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public MyFriendsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.friendslist_card_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFriendsListAdapter.MyViewHolder holder, int position) {
        Friend friend = list.get(position);

        holder.username.setText(friend.getUsername());
        holder.balance.setText(friend.getBalance().toString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(list.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username, balance;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.TVfriendname);
            balance = itemView.findViewById(R.id.TVdebtamount);
            cardView = itemView.findViewById(R.id.friendslist_card_row);
        }
    }
}
