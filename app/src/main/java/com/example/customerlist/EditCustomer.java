package com.example.customerlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class EditCustomer extends AppCompatActivity {
    private int mCustomerId;
    private EditText edtCustomerName, edtCustomerAge, edtCustomerImageUrl;
    private Switch swAddImage;
    private Button btnEditCustomer, btnBack, btnDeleteCustomer;
    private boolean isImageAvailable;
    private CustomerDbHelper dbHelper;
    private CustomerModel customer;
    private int customerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);

        edtCustomerName = findViewById(R.id.edt_customer_name);
        edtCustomerAge = findViewById(R.id.edt_customer_age);
        edtCustomerImageUrl = findViewById(R.id.edt_customer_image_url);
        swAddImage = findViewById(R.id.sw_add_image);
        btnEditCustomer = findViewById(R.id.btn_edit_customer);
        btnBack = findViewById(R.id.btn_back_to_home);
        btnDeleteCustomer = findViewById(R.id.btn_delete_customer);
        dbHelper = new CustomerDbHelper(this);

        setCustomerId();
        showCustomer();

        swAddImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    edtCustomerImageUrl.setVisibility(View.VISIBLE);
                }else {
                    edtCustomerImageUrl.setVisibility(View.GONE);
                }
            }
        });

        btnEditCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCustomer();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditCustomer.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDeleteCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCustomer();
            }
        });
    }

    private void showCustomer() {
        customer = dbHelper.getCustomer(customerId);
        if (customer == null){
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }else {
            edtCustomerName.setText(customer.getName());
            edtCustomerAge.setText(String.valueOf(customer.getAge()));
            if (!customer.getImageUrl().equals("")){
                edtCustomerImageUrl.setText(customer.getImageUrl());
                edtCustomerImageUrl.setVisibility(View.VISIBLE);
                swAddImage.setChecked(true);
            }
        }
    }

    private void editCustomer(){
        String name = edtCustomerName.getText().toString();
        int age = Integer.parseInt(edtCustomerAge.getText().toString());
        String imageUrl;
        if (swAddImage.isChecked()){
            imageUrl = edtCustomerImageUrl.getText().toString();
        }else {
            imageUrl = "";
        }

        boolean updated = dbHelper.editCustomer(customerId,name, age, imageUrl);
        if (updated){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Something wrong, update failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void setCustomerId() {
        Intent intent = getIntent();
        this.customerId = intent.getIntExtra("Customer_id", -1);
    }

    private void deleteCustomer(){
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.delete_customer))
                .setMessage(getString(R.string.customer_delete_dialog))
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(dbHelper.deleteCustomer(customerId)){
                            Intent intent = new Intent(EditCustomer.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(EditCustomer.this, "Something went wrong while deleting customer!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .show();

    }

}