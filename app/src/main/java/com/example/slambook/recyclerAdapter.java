package com.example.slambook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
private Context context;

private List<model> list;
private itemOnClick listener;


 recyclerAdapter(Context context, List<model> list, itemOnClick listener)
    {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_id, txt_author, txt_date;
        CardView card_slam;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_slamID);
            txt_author = itemView.findViewById(R.id.txt_slamAuthor);
            txt_date = itemView.findViewById(R.id.txt_slamDate);
            card_slam = itemView.findViewById(R.id.card_slam);
        }
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.slam_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     int adapterpos = holder.getAdapterPosition();
    holder.txt_id.setText(list.get(adapterpos).getSlam_id());
    holder.txt_author.setText(list.get(adapterpos).getSlam_author());
    holder.txt_date.setText(list.get(adapterpos).getSlam_date());
    holder.card_slam.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onItemClicked(list.get(adapterpos));
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
