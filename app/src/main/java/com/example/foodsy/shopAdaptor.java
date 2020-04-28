package com.example.foodsy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class shopAdaptor extends RecyclerView.Adapter<shopAdaptor.myViewHolder>{

    ArrayList<item> itemArrayList =new ArrayList<>();
    Context context;

    /*public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private AdapterView.OnItemClickListener mListner;

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListner= listener;


    }*/

    public shopAdaptor(ArrayList<item> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shop_item,parent,false);

        return new myViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        item current = itemArrayList.get(position);

        holder.nameTV.setText(current.getName());
        holder.priceTV.setText(current.getPrice());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, priceTV;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTV=itemView.findViewById(R.id.shop_item_name);
            priceTV=itemView.findViewById(R.id.shop_item_price);

        }
    }
}
