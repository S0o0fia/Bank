package com.example.safaanabil.bank;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Safaa Nabil on 4/28/2018.
 */

public class Curadapter  extends RecyclerView.Adapter<Curadapter.holder> {

    ArrayList<Currency> currencies;
    Context con ;

    public Curadapter (ArrayList<Currency> currencies , Context con) {
        this.currencies = currencies;
        this.con = con;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cur_pos , parent , false );

        holder holder = new holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {
        holder.name.setText(currencies.get(position).getName());
        holder.buy.setText(currencies.get(position).getBuy());
        holder.sell.setText(currencies.get(position).getSell());
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView name , buy, sell ;



        public holder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            buy = (TextView) itemView.findViewById(R.id.buy);
            sell = (TextView) itemView.findViewById(R.id.sell);


        }
    }
}


