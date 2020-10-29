package com.example.customerlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecViewCustomerAdaptor extends RecyclerView.Adapter <RecViewCustomerAdaptor.ViewHolder> {
    private ArrayList <CustomerModel> customers = new ArrayList<>();
    private Context context;
    public RecViewCustomerAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customer_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO: find a way so u can translate "Name" and "age" strings in bellow statements
        holder.txtCustomerName.setText("Name: " + customers.get(position).getName());
        holder.txtCustomerAge.setText("Age " +Integer.toString(customers.get(position).getAge()));
            //TODO: Show avatar Image

        holder.clCustomerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, customers.get(position).getName() + " Clicked", Toast.LENGTH_SHORT).show();
                //TODO: Add onclick option for each customer item
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCustomerName, txtCustomerAge;
        ConstraintLayout clCustomerItem;
        ImageView imgCustomerAvatar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCustomerName = itemView.findViewById(R.id.txt_customer_item_name);
            txtCustomerAge = itemView.findViewById(R.id.txt_customer_item_age);
            clCustomerItem = itemView.findViewById(R.id.customer_item);
            imgCustomerAvatar = itemView.findViewById(R.id.img_customer_avatar);
        }
    }

    public void setCustomers(ArrayList<CustomerModel> customers) {
        this.customers = customers;
        notifyDataSetChanged();
    }
}
