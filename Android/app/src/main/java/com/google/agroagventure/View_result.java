package com.google.agroagventure;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class View_result extends AppCompatActivity {
    ListView v;
    SharedPreferences sh;
    String ip,url,feedb,rates;
    ArrayList<String> crop,details,image,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        v=(ListView)findViewById(R.id.list);
        sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ip=sh.getString("ip", "");

        String url ="http://"+ip+":5000/crop_prediction";

        RequestQueue queue = Volley.newRequestQueue(View_result.this);


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
//                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                    crop=new ArrayList<String>();
                    details=new ArrayList<String>();
                    image=new ArrayList<String>();





                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        crop.add(jo.getString("crop"));
                        details.add(jo.getString("details"));
                        image.add(jo.getString("image"));

                    }
//                      ArrayAdapter<String> ad=new ArrayAdapter<String>(result.this,android.R.layout.simple_list_item_1,crop);
//                      v.setAdapter(ad);

                    v.setAdapter(new custimg(View_result.this, crop, image,details));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"No match found",Toast.LENGTH_LONG).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Toast.makeText(getApplicationContext(),"Error"+error,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("humi", sh.getString("hum",""));
                params.put("temp", sh.getString("temp",""));
                params.put("wet", sh.getString("wet",""));



                return params;
            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
}
