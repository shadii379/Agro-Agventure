package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Equipment_payment extends AppCompatActivity {

    EditText e1,e2,e3;
    Button b1;
    TextView t1;
    String total,url;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_payment);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        t1=findViewById(R.id.textView59);
        total=sh.getString("total","");



        t1.setText(total);

        e1=findViewById(R.id.editTextTextPersonName10);
        e2=findViewById(R.id.editTextTextPersonName21);
        e3=findViewById(R.id.editTextTextPersonName22);

        b1=findViewById(R.id.button22);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String acno=e1.getText().toString();
                final String ifsc=e2.getText().toString();
                final String pin=e3.getText().toString();
                if(acno.equals(""))
                {
                    e1.setError("enter accno");
                }
                else if(ifsc.equals(""))
                {
                    e2.setError("enter ifsc");
                }
                else if(pin.equals(""))
                {
                    e3.setError("enter pin");
                }
                else {

                    RequestQueue queue = Volley.newRequestQueue(Equipment_payment.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/bank";

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


                                    Toast.makeText(Equipment_payment.this, "Paid successfully", Toast.LENGTH_SHORT).show();

                                    Intent in = new Intent(getApplicationContext(), farmerHome.class);
                                    startActivity(in);
//                                    Intent i = new Intent(getApplicationContext(), LocationService.class);
//                                    startService(i);


                                } else {
                                    Toast.makeText(Equipment_payment.this, status, Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                                Toast.makeText(Equipment_payment.this, "" + e, Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Equipment_payment.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("accno", acno);
                            params.put("ifsc", ifsc);
                            params.put("key", pin);
                            params.put("total", total);
                            params.put("fid", sh.getString("lid", ""));
                            params.put("bil_equ_lid", sh.getString("oid", ""));

                            return params;
                        }
                    };
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);


                }

            }
        });
    }
}