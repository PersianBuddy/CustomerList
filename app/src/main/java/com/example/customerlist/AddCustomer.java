package com.example.customerlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;

public class AddCustomer extends AppCompatActivity {
    private EditText edtCustomerName, edtCustomerAge, edtCustomerImageUrl;
    private Switch swAddImage;
    private Button btnAddCustomer;
    private boolean isImageAvailable;
    private CustomerDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        edtCustomerName = findViewById(R.id.edt_customer_name);
        edtCustomerAge = findViewById(R.id.edt_customer_age);
        edtCustomerImageUrl = findViewById(R.id.edt_customer_image_url);
        swAddImage = findViewById(R.id.sw_add_image);
        btnAddCustomer = findViewById(R.id.btn_add_customer);
        dbHelper = new CustomerDbHelper(this);

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

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomer();
                Intent intent = new Intent(AddCustomer.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void addCustomer(){
        String name = edtCustomerName.getText().toString();
        int age = Integer.parseInt(edtCustomerAge.getText().toString());
        String imageUrl = edtCustomerImageUrl.getText().toString();
        CustomerModel customer = new CustomerModel(name, age,imageUrl);
       dbHelper.addCustomer(customer);
    }
}