package com.pulsetech.cepdefteri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddCustomersActivity extends AppCompatActivity {


    EditText customerName, Phone, editTextAddress, Taxes, IdentityNo, Mail;
    Button btnSave;
    FirebaseDatabase database;
    private HashMap<String, Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_customers);



        customerName = findViewById(R.id.editTextCustomerName);
        Phone = findViewById(R.id.editTextPhone);
        editTextAddress = findViewById(R.id.editTextAddress);
        Taxes = findViewById(R.id.editTextTaxes);
        IdentityNo = findViewById(R.id.editTextIdentityNo);
        Mail = findViewById(R.id.editTextMailAddress);
        btnSave = findViewById(R.id.buttonSaveCustomer);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database();
            }
        });


    }


    void Database() {
        mData = new HashMap<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        String CustomerName = customerName.getText().toString();
        String PhoneNumber = Phone.getText().toString();
        String Address = editTextAddress.getText().toString();
        String Tax = Taxes.getText().toString();
        String Identity = IdentityNo.getText().toString();
        String MailAddress = Mail.getText().toString();


        mData.put("CustomerName", CustomerName);
        mData.put("PhoneNumber", PhoneNumber);
        mData.put("Address", Address);
        mData.put("Tax", Tax);
        mData.put("Identity", Identity);
        mData.put("MailAddress", MailAddress);

        myRef.setValue(mData)
                .addOnCompleteListener(AddCustomersActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(AddCustomersActivity.this, "Müşteri başarıyla eklendi!", Toast.LENGTH_SHORT).show();
                            Log.d("TAG", "Veritabanına veri başarıyla eklendi!");
                            startActivity(new Intent(AddCustomersActivity.this, CustomersActivity.class));

                        } else {
                            Toast.makeText(AddCustomersActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }

}