package com.example.YouOweMeProject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.YouOweMeProject.Model.Friend;
import com.example.YouOweMeProject.Model.History;
import com.example.YouOweMeProject.R;

import java.util.ArrayList;

public class MyHisotryAdapter extends RecyclerView.Adapter<MyHisotryAdapter.MyViewHolder>{
    Context context;
    ArrayList<History> listOfHistory;

    public MyHisotryAdapter(Context context, ArrayList<History> listOfHistory) {
        this.context = context;
        this.listOfHistory = listOfHistory;
    }

    @NonNull
    @Override
    public MyHisotryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_item_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHisotryAdapter.MyViewHolder holder, int position) {
        History history = listOfHistory.get(position);

        holder.description.setText(history.getDescription());
        holder.date.setText(history.getDate().toString());
    }

    @Override
    public int getItemCount() {
        return listOfHistory.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView description, date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.TVHistoryItemRowDescription);
            date = itemView.findViewById(R.id.TVHistoryDate);
        }
    }
}
