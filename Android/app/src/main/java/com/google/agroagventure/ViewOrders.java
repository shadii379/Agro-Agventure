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

public class ViewOrders extends AppCompatActivity {
    ListView lv1;
    Button b1,b2;

    SharedPreferences sh;
    ArrayList<String> pname,quantity;
    String url,bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lv1=findViewById(R.id.lv1);
        b1=findViewById(R.id.button8);
        b2=findViewById(R.id.button9);
        bid=getIntent().getStringExtra("bill");

        url ="http://"+sh.getString("ip","")+":5000/order_details";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(ViewOrders.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    pname=new ArrayList<String>();
                    quantity=new ArrayList<String>();


                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        pname.add(job.getString("Product_Name"));
                        quantity.add(job.getString("Quantity"));



                    }
//

                    lv1.setAdapter(new custom2(ViewOrders.this,pname,quantity));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(ViewOrders.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ViewOrders.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("billid",getIntent().getStringExtra("bill"));
                return params;
            }
        };


// Add the request to the RequestQueue.
        queue.add(stringRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(ViewOrders.this);
                url = "http://" + sh.getString("ip", "") + ":5000/acceptorder";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.

                        try {
                            JSONObject jo = new JSONObject(response);
                            String status = jo.getString("task");
//                                Toast.makeText(Login.this, status, Toast.LENGTH_SHORT).show();

                            if (status.equalsIgnoreCase("success")) {

                                Toast.makeText(ViewOrders.this, "Accepted", Toast.LENGTH_SHORT).show();

                                Intent
                                        in = new Intent(getApplicationContext(), ViewProductOrder.class);
                                startActivity(in);



                            } else {
                                Toast.makeText(ViewOrders.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                            Toast.makeText(ViewOrders.this, "" + e, Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewOrders.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("billid",getIntent().getStringExtra("bill"));



                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(ViewOrders.this);
                url = "http://" + sh.getString("ip", "") + ":5000/rejecttorder";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.

                        try {
                            JSONObject jo = new JSONObject(response);
                            String status = jo.getString("task");
//                                Toast.makeText(Login.this, status, Toast.LENGTH_SHORT).show();

                            if (status.equalsIgnoreCase("success")) {

                                Toast.makeText(ViewOrders.this, "rejected", Toast.LENGTH_SHORT).show();

                                Intent
                                        in = new Intent(getApplicationContext(), ViewProductOrder.class);
                                startActivity(in);



                            } else {
                                Toast.makeText(ViewOrders.this, "error", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                            Toast.makeText(ViewOrders.this, "" + e, Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewOrders.this, "err"+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();
                        params.put("billid",getIntent().getStringExtra("bill"));



                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }
}