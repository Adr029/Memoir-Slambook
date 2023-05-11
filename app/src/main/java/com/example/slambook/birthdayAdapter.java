package com.example.slambook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class birthdayAdapter extends RecyclerView.Adapter<birthdayAdapter.MyViewHolder> {

    private Context context;
    private ArrayList author, bday;
    public birthdayAdapter(Context context, ArrayList author, ArrayList bday)
    {
        this.context = context;
        this.author = author;
        this.bday = bday;
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_author, txt_bday;
        CardView card_slam;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_author = itemView.findViewById(R.id.txt_slamAuthor2);
            txt_bday = itemView.findViewById(R.id.txt_slamBirthDate);
            card_slam = itemView.findViewById(R.id.card_slam2);
        }
    }

    @NonNull
    @Override
    public birthdayAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.birthday_layout, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull birthdayAdapter.MyViewHolder holder, int position) {
        holder.txt_author.setText(String.valueOf(author.get(position)));
        holder.txt_bday.setText(String.valueOf(bday.get(position)));

    }

    public int getItemCount() {
        return author.size();
    }
}
