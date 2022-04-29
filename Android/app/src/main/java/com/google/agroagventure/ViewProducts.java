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

public class ViewProducts extends AppCompatActivity {
    ListView lv1;
    Button b1;

    SharedPreferences sh;
    ArrayList<String> name,description;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lv1=findViewById(R.id.lv1);
        b1=findViewById(R.id.button);

        url ="http://"+sh.getString("ip","")+":5000/view_products";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(ViewProducts.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    name=new ArrayList<String>();
                    description=new ArrayList<String>();


                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        name.add(job.getString("Product_Name"));
                        description.add(job.getString("Description"));



                    }
//

                    lv1.setAdapter(new custom2(ViewProducts.this,name,description));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(ViewProducts.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ViewProducts.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };


// Add the request to the RequestQueue.
        queue.add(stringRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AddProducts.class);
                startActivity(i);
            }
        });
    }
}