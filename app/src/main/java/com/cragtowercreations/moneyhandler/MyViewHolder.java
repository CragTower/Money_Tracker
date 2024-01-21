package com.cragtowercreations.moneyhandler;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView amountView;
    TextView descView;
    ImageButton button;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        amountView = itemView.findViewById(R.id.amountView);
        descView = itemView.findViewById(R.id.descView);
        button = itemView.findViewById(R.id.deleteTransactionButton);
    }


}
