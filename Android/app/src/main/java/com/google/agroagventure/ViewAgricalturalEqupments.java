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

public class ViewAgricalturalEqupments extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv1;

    SharedPreferences sh;
    ArrayList<String> image,name,description,eqpmntid,price,eid;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_agricaltural_equpments);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        lv1=findViewById(R.id.lv1);

        url ="http://"+sh.getString("ip","")+":5000/view_equpment";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(ViewAgricalturalEqupments.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    image=new ArrayList<String>();
                    name=new ArrayList<String>();
                    description=new ArrayList<String>();
                    eqpmntid=new ArrayList<String>();
                    price=new ArrayList<String>();
                    eid=new ArrayList<String>();

                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);
                        image.add(job.getString("Image"));
                        name.add(job.getString("Name"));
                        description.add(job.getString("Description"));
                        eqpmntid.add(job.getString("Equipment_ID"));
                        price.add(job.getString("Price"));
                        eid.add(job.getString("Equipment_ID"));


                    }
//

                    lv1.setAdapter(new Customimg(ViewAgricalturalEqupments.this,image,name,description));
//                    ArrayAdapter<String> ad=new ArrayAdapter<>(VIEWEXTERIORDESIGN.this,android.R.layout.simple_list_item_1,title);
//                    lv2.setAdapter(ad);
                    lv1.setOnItemClickListener(ViewAgricalturalEqupments.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(ViewAgricalturalEqupments.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ViewAgricalturalEqupments.this, "err"+error, Toast.LENGTH_SHORT).show();
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
        Intent i=new Intent(getApplicationContext(), OrderAgricalturalEqupments.class);

        i.putExtra("id", eid.get(position));
        i.putExtra("image", image.get(position));
        i.putExtra("name", name.get(position));
        i.putExtra("description", description.get(position));
        i.putExtra("price", price.get(position));
        i.putExtra("eqpmntid", eqpmntid.get(position));


        startActivity(i);



    }
}