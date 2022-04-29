package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class farmerHome extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b8,b9;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_home);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1=findViewById(R.id.button11);
        b2=findViewById(R.id.button20);
        b3=findViewById(R.id.button13);
//        b4=findViewById(R.id.button14);
        b5=findViewById(R.id.button15);
        b6=findViewById(R.id.button16);
        b8=findViewById(R.id.button18);
        b9=findViewById(R.id.button19);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), ViewProducts.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), ViewAgricalturalEqupments.class);
                startActivity(i2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getApplicationContext(), ViewOrderDetails.class);
                startActivity(i3);
            }
        });

//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i4=new Intent(getApplicationContext(), Payment.class);
//                startActivity(i4);
//            }
//        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i5=new Intent(getApplicationContext(), ViewProductOrder.class);
                startActivity(i5);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i6=new Intent(getApplicationContext(), Complaints.class);
                startActivity(i6);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i7=new Intent(getApplicationContext(),Crop_prediction.class );
                startActivity(i7);
            }
        });
//
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i9=new Intent(getApplicationContext(), Login.class);
                startActivity(i9);
            }
        });

    }
}