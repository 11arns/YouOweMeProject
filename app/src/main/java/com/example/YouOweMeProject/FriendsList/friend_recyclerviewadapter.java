package com.example.YouOweMeProject.FriendsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.YouOweMeProject.R;

import java.util.ArrayList;

public class friend_recyclerviewadapter extends RecyclerView.Adapter<friend_recyclerviewadapter.MyViewHolder> {

   Context context;
   ArrayList<friendmodel> friendmodels;

    public friend_recyclerviewadapter(Context context, ArrayList<friendmodel> friendmodels){
        this.context= context;
        this.friendmodels = friendmodels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.friend_recyclerviewrow,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(friendmodels.get(position).getExpensename());
        holder.status.setText(friendmodels.get(position).getExpensestatus());
        holder.amount.setText(friendmodels.get(position).getExpenseamount());
    }

    @Override
    public int getItemCount() {
        return friendmodels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, status, amount;

        public MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.expensenameTV);
            status = itemView.findViewById(R.id.expensestatusTV);
            amount = itemView.findViewById(R.id.expenseamountTV);
        }
    }
}
