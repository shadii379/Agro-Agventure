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

public class View_replay extends AppCompatActivity {

    ListView lv1;


    SharedPreferences sh;
    ArrayList<String> complaint,date,replay;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_replay);

        lv1=findViewById(R.id.lv1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip","")+":5000/view_replay";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(View_replay.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    complaint=new ArrayList<String>();
                    date=new ArrayList<String>();
                    replay=new ArrayList<String>();

                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        complaint.add(job.getString("Shop_Name")+"-"+job.getString("Complaint"));
                        date.add(job.getString("Date"));
                        replay.add(job.getString("Reply"));


                    }
//

                    lv1.setAdapter(new custom3(View_replay.this,complaint,date,replay));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(View_replay.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(View_replay.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid",""));
                return params;
            }
        };


// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}