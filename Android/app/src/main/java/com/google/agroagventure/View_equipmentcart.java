package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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

public class View_equipmentcart extends AppCompatActivity {
    ListView l1;
    Button b1;
    SharedPreferences sh;
    String url;
    ArrayList<String> product,price,quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_equipmentcart);
        l1=findViewById(R.id.list);
        b1=findViewById(R.id.button21);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        url ="http://"+sh.getString("ip","")+":5000/view_equipmentcart";
        b1.setVisibility(View.INVISIBLE);
       if(getIntent().getStringExtra("b1").equals("yes"))
       {
           b1.setVisibility(View.VISIBLE);
       }
       else
       {
           b1.setVisibility(View.INVISIBLE);
       }


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Equipment_payment.class);
                startActivity(i);
            }
        });



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(View_equipmentcart.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    product=new ArrayList<String>();
                    price=new ArrayList<String>();
                    quantity=new ArrayList<String>();



                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        product.add(job.getString("Name"));
                        price.add(job.getString("Price"));
                        quantity.add(job.getString("quantity"));



                    }
//

                        l1.setAdapter(new custom3(View_equipmentcart.this,product,price,quantity));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(View_equipmentcart.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(View_equipmentcart.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();


                params.put("oid", sh.getString("oid",""));
                params.put("fid", sh.getString("lid",""));
                params.put("b1",getIntent().getStringExtra("b1"));


                return params;
            }
        };


// Add the request to the RequestQueue.
        queue.add(stringRequest);






    }
}