package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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

public class Complaints extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    EditText e1;
    Button b1;
    TextView tv;
    ArrayList<String> Shop_Name,sid;

    String cmpt,url;
    String sh_id="";
    String skid="";
    SharedPreferences sh;
Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

tv=findViewById(R.id.textView50);
        e1=findViewById(R.id.editTextTextPersonName);
        b1=findViewById(R.id.button10);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),View_replay.class);
                startActivity(i);
            }
        });
        sp=findViewById(R.id.spinner);




//        ========================================================================


        url ="http://"+sh.getString("ip","")+":5000/view_shops";



        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(Complaints.this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.

                try {
                    JSONArray jo = new JSONArray(response);
                    Shop_Name=new ArrayList<String>();
                    sid=new ArrayList<String>();


                    for (int i=0;i<jo.length();i++){
                        JSONObject job=jo.getJSONObject(i);

                        Shop_Name.add(job.getString("Shop_Name"));
                        sid.add(job.getString("Login_ID"));


                    }



                    ArrayAdapter<String> ad=new ArrayAdapter<>(Complaints.this,android.R.layout.simple_list_item_1,Shop_Name);
                    sp.setAdapter(ad);

                    sp.setOnItemSelectedListener( Complaints.this);

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                    Toast.makeText(Complaints.this, e+"", Toast.LENGTH_SHORT).show();
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Complaints.this, "err"+error, Toast.LENGTH_SHORT).show();
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








//        =================================================================

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cmpt=e1.getText().toString();

                if(cmpt.equalsIgnoreCase(""))
                {
                    e1.setError("ENTER YOUR COMPLAINT...!!!");
                }
                else {


                    RequestQueue queue = Volley.newRequestQueue(Complaints.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/post_complaints";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.

                            try {
                                JSONObject jo = new JSONObject(response);
                                String status = jo.getString("task");
                                Toast.makeText(Complaints.this, status, Toast.LENGTH_SHORT).show();

                                if (status.equalsIgnoreCase("SUCCESS")) {



                                    Toast.makeText(Complaints.this, status, Toast.LENGTH_SHORT).show();

                                    Intent
                                            in = new Intent(getApplicationContext(), Complaints.class);
                                    startActivity(in);


                                }

                                else
                                {


                                    Toast.makeText(Complaints.this, "FAILED...!!!", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                                Toast.makeText(Complaints.this, "" + e, Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Complaints.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("complaints", cmpt);
                            params.put("fid", sh.getString("lid",""));
                            params.put("u_id",skid);


                            return params;
                        }
                    };
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        skid=sid.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
