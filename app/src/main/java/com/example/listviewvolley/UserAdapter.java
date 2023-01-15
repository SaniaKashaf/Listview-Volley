package com.example.listviewvolley;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    ArrayList<UserModel> userList;
    Context context;

    public UserAdapter(ArrayList<UserModel> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view= LayoutInflater.from(context).inflate(R.layout.list_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
UserModel userModel=userList.get(position);

holder.textView1.setText(userModel.getFirst_name()+" "+userModel.last_name );
holder.textView2.setText(userModel.getEmail());

        Glide.with(context).load(userModel.avatar).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView1,textView2;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        textView1=itemView.findViewById(R.id.txt_1);
        textView2=itemView.findViewById(R.id.txt_2);
        cardView=itemView.findViewById(R.id.cardView);
        imageView=itemView.findViewById(R.id.img);



        }
    }
}
