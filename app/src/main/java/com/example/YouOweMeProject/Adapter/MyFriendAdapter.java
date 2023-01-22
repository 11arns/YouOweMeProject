package com.example.YouOweMeProject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.YouOweMeProject.Model.Expense;
import com.example.YouOweMeProject.R;

import java.util.ArrayList;

public class MyFriendAdapter extends RecyclerView.Adapter<MyFriendAdapter.MyViewHolder> {
    Context context;
    ArrayList<Expense> listOfExpense;
    SelectListenerExpense selectListener;

    public MyFriendAdapter(Context context, ArrayList<Expense> listOfExpense, SelectListenerExpense selectListener) {
        this.context = context;
        this.listOfExpense = listOfExpense;
        this.selectListener = selectListener;
    }

    @NonNull
    @Override
    public MyFriendAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.friend_recyclerviewrow, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFriendAdapter.MyViewHolder holder, int position) {
        Expense expense = listOfExpense.get(position);

        holder.amount.setText(expense.getAmount().toString());
        holder.nameOfExpense.setText(expense.getNameOfExpense());
        holder.type.setText(expense.getType());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectListener.onItemClicked(listOfExpense.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfExpense.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView amount, nameOfExpense, type;
        public Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.expenseamountTV);
            nameOfExpense = itemView.findViewById(R.id.expensenameTV);
            type = itemView.findViewById(R.id.expensestatusTV);
            button = itemView.findViewById(R.id.btnsettleup);
        }
    }

}
