package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

public class Payment extends AppCompatActivity {
    ListView lv1;

    SharedPreferences sh;
    ArrayList<String> sname,amount,date;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lv1=findViewById(R.id.lv1);


        url ="http://"+sh.getString("ip","")+":5000/payment_details_farmer";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(Payment.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    sname=new ArrayList<String>();
                    amount=new ArrayList<String>();
                    date=new ArrayList<String>();


                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        sname.add(job.getString("`Shop_Name"));
                        amount.add(job.getString("Total_Price"));
                        date.add(job.getString("Date"));



                    }
//

                    lv1.setAdapter(new custom3(Payment.this,sname,amount,date));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(Payment.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Payment.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fid",sh.getString("lid",""));
                return params;
            }
        };


// Add the request to the RequestQueue.
        queue.add(stringRequest);




    }
}