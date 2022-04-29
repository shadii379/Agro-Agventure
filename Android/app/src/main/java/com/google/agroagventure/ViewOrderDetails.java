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

public class ViewOrderDetails extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv1;

    SharedPreferences sh;
    ArrayList<String> date,tprice,Bill_Equipment_ID;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_details);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lv1=findViewById(R.id.lv1);
        lv1.setOnItemClickListener(this);

        url ="http://"+sh.getString("ip","")+":5000/equpment_order";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(ViewOrderDetails.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    date=new ArrayList<String>();
                    tprice=new ArrayList<String>();
                    Bill_Equipment_ID=new ArrayList<String>();

                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        date.add(job.getString("Date"));
                        tprice.add(job.getString("Total_Price"));
                        Bill_Equipment_ID.add(job.getString("Bill_Equipment_ID"));


                    }
//

                    lv1.setAdapter(new custom3(ViewOrderDetails.this,Bill_Equipment_ID,tprice,date));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(ViewOrderDetails.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ViewOrderDetails.this, "err"+error, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent ik=new Intent(getApplicationContext(),View_equipmentcart.class);
        SharedPreferences.Editor ed=sh.edit();
        ed.putString("oid",Bill_Equipment_ID.get(position));
        ed.commit();
        ik.putExtra("b1","no");
        startActivity(ik);

    }
}