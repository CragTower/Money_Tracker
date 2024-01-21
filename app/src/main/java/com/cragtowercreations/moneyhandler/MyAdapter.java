package com.cragtowercreations.moneyhandler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private ArrayList<TransactionItems> transactionList;
    AppDatabase DB;

    public MyAdapter(Context context, ArrayList<TransactionItems> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.transaction_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int arrayPos = holder.getAdapterPosition();
        int id = transactionList.get(position).getId();
        if (transactionList.get(position).getIncome() != "null") {
            holder.amountView.setText(transactionList.get(position).getIncome());
        }
        else {
            holder.amountView.setText(transactionList.get(position).getExpense());
        }
        holder.button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DB = new AppDatabase(context);

                DB.deleteEntry(id);
                transactionList.remove(arrayPos);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}
