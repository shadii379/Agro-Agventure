package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

public class ViewProductOrder extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv1;

    SharedPreferences sh;
    ArrayList<String> sname,date,tprice,billid;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product_order);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lv1=findViewById(R.id.lv1);
        lv1.setOnItemClickListener(this);

        url ="http://"+sh.getString("ip","")+":5000/bill";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(ViewProductOrder.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    sname=new ArrayList<String>();
                    date=new ArrayList<String>();
                    tprice=new ArrayList<String>();
                    billid=new ArrayList<String>();

                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        sname.add(job.getString("Shop_Name"));
                        date.add(job.getString("Date"));
                        tprice.add(job.getString("Total_Price"));
                        billid.add(job.getString("Bill_ID"));

                    }
//

                    lv1.setAdapter(new custom3(ViewProductOrder.this,sname,date,tprice));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(ViewProductOrder.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ViewProductOrder.this, "err"+error, Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent in = new Intent(getApplicationContext(), ViewOrders.class);
        in.putExtra("bill",billid.get(position));
        startActivity(in);
    }
}