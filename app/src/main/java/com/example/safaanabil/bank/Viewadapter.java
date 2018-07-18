package com.example.safaanabil.bank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Safaa Nabil on 4/30/2018.
 */

public class Viewadapter extends RecyclerView.Adapter<Viewadapter.holder> {

    ArrayList<viewinfo> viewinfos;
    Context con ;

    public Viewadapter (ArrayList<viewinfo> viewinfos , Context con) {
        this.viewinfos = viewinfos;
        this.con = con;
    }

    @Override
    public Viewadapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_info , parent , false );

        Viewadapter.holder holder = new Viewadapter.holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        holder.amount.setText(viewinfos.get(position).getAmount());
        holder.dtime.setText(viewinfos.get(position).getDtime());
        holder.reciever.setText(viewinfos.get(position).getAcumrev());
    }

    @Override
    public int getItemCount() {
        return viewinfos.size();
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView amount ,dtime, reciever ;



        public holder(View itemView) {
            super(itemView);
            amount = (TextView) itemView.findViewById(R.id.amount);
            dtime = (TextView) itemView.findViewById(R.id.dtime);
            reciever = (TextView) itemView.findViewById(R.id.reciever);


        }
    }
}




