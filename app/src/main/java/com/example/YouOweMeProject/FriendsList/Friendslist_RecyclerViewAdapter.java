package com.example.YouOweMeProject.FriendsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.YouOweMeProject.R;

import java.util.ArrayList;

public class Friendslist_RecyclerViewAdapter extends RecyclerView.Adapter<Friendslist_RecyclerViewAdapter.myviewholder> {
    private final FriendsListInterface friendsListInterface;

    Context context;
    ArrayList<FriendsListModel> friendsListModels;

    public Friendslist_RecyclerViewAdapter(Context context, ArrayList<FriendsListModel> friendsListModels, FriendsListInterface friendsListInterface){
       this.context = context;
       this.friendsListModels= friendsListModels;
       this.friendsListInterface = friendsListInterface;

    }
    @NonNull
    @Override
    public Friendslist_RecyclerViewAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.friendslist_recyclerviewrow, parent, false);
        return new Friendslist_RecyclerViewAdapter.myviewholder(view, friendsListInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull Friendslist_RecyclerViewAdapter.myviewholder holder, int position) {
        holder.tvname.setText(friendsListModels.get(position).getFriendname());
        holder.tvdebtamount.setText(friendsListModels.get(position).getDebtamount());
        holder.tvdebtstatus.setText(friendsListModels.get(position).getDebtstatus());
        holder.imageView.setImageResource(friendsListModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return friendsListModels.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView tvname, tvdebtstatus, tvdebtamount;

        public myviewholder(@NonNull View itemView, FriendsListInterface friendsListInterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IVfriends);
            tvname = itemView.findViewById(R.id.TVfriendname);
            tvdebtstatus = itemView.findViewById(R.id.TVdebtstatus);
            tvdebtamount = itemView.findViewById(R.id.TVdebtamount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(friendsListInterface!=null){
                        int pos = getAdapterPosition();

                        if( pos != RecyclerView.NO_POSITION){
                            friendsListInterface.onitemclick(pos);
                        }
                    }
                }
            });


        }
    }
}
