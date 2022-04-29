package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Crop_prediction extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;
    SharedPreferences sh;
    String humidity,wet,temp,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_prediction);
        e1=findViewById(R.id.editTextTextPersonName23);
        e2=findViewById(R.id.editTextTextPersonName24);
        e3=findViewById(R.id.editTextTextPersonName25);
        b1=findViewById(R.id.button14);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humidity=e1.getText().toString();
                wet=e3.getText().toString();
                temp=e2.getText().toString();
                if(humidity.equals(""))
                {
                    e1.setError("enter humidity");
                }
                else if(temp.equals(""))
                {
                    e2.setError("enter temperature");
                }
                else if(wet.equals(""))
                {
                    e3.setError("enter wet");
                }
                else {

                   Intent ik=new Intent(getApplicationContext(),View_result.class);
                    SharedPreferences.Editor ed=sh.edit();
                    ed.putString("hum",humidity);
                    ed.putString("wet",wet);
                    ed.putString("temp",temp);
                    ed.commit();
                   startActivity(ik);

                }

            }
        });
    }
}