package com.allbank.balancecheck.bankadapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.allbank.balancecheck.BankModel;
import com.allbank.balancecheck.HomePageActivity;
import com.allbank.balancecheck.R;
import java.util.ArrayList;

public class SwitzerlandBankAdapter extends RecyclerView.Adapter<SwitzerlandBankAdapter.ViewHolder> {

    Context context;
    ArrayList<BankModel> SwitzerlandBanks;

    public SwitzerlandBankAdapter(Context context, ArrayList<BankModel> SwitzerlandBanks){
        this.context = context;
        this.SwitzerlandBanks = SwitzerlandBanks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_design,parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(SwitzerlandBanks.get(position).img);
        holder.bank_name.setText(SwitzerlandBanks.get(position).bankname);
    }

    @Override
    public int getItemCount() {
        return SwitzerlandBanks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView bank_name;
        ImageView imageView;
        CardView bank_cardview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bank_name = itemView.findViewById(R.id.bank_name);
            imageView = itemView.findViewById(R.id.img_bank);
            bank_cardview = itemView.findViewById(R.id.bank_cardview);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, HomePageActivity.class);
            intent.putExtra("bankname",bank_name.getText().toString());
            context.startActivity(intent);
        }
    }
}
