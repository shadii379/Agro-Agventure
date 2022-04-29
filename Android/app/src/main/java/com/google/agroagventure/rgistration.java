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

public class rgistration extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10;
    Button b1;

    String fname,lname,phone,post,place,pin,district,uname,passwrd,rpaswrd;
    SharedPreferences sh;
    String url,ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgistration);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=findViewById(R.id.editTextTextPersonName13);
        e2=findViewById(R.id.editTextTextPersonName14);
        e3=findViewById(R.id.editTextTextPersonName15);
        e4=findViewById(R.id.editTextTextPersonName16);
        e5=findViewById(R.id.editTextTextPersonName17);
        e6=findViewById(R.id.editTextTextPersonName18);
        e7=findViewById(R.id.editTextTextPersonName19);
        e8=findViewById(R.id.editTextTextPersonName20);
        e9=findViewById(R.id.editTextTextPassword);
        e10=findViewById(R.id.editTextTextPassword2);

        b1=findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=e1.getText().toString();
                lname=e2.getText().toString();
                phone=e3.getText().toString();
                post=e4.getText().toString();
                place=e5.getText().toString();
                pin=e6.getText().toString();
                district=e7.getText().toString();
                uname=e8.getText().toString();
                passwrd=e9.getText().toString();
                rpaswrd=e10.getText().toString();
                if (fname.equals(""))
                {
                    e1.setError("enter firstname");
                }
                else if((!fname.matches("^[a-zA-Z]*$"))) {
                    e1.setError("characters allowed");
                }
                else if(lname.equals(""))
                {
                    e2.setError("enter lastname");
                }
                else if((!lname.matches("^[a-zA-Z]*$"))) {
                    e2.setError("characters allowed");
                }
                else if(phone.equals(""))
                {
                    e3.setError("enter phone number");
                }
                else if(phone.length()!=10)
                {
                    e3.setError("Minimum 10 nos required");
                    e3.requestFocus();
                }
                else if(post.equals(""))
                {
                    e4.setError("enter post");
                }

                else if(place.equals(""))
                {
                    e5.setError("enter place");
                }

                else if (pin.equals(""))
                {
                    e6.setError("enter pin");
                }
                else if(pin.length()!=6)
                {
                    e6.setError("Maximum 6 nos required");
                    e6.requestFocus();
                }
                else if(district.equals(""))
                {
                    e7.setError("enter district");
                }

                else if(uname.equals(""))
                {
                    e8.setError("enter username");
                }
                else if(passwrd.equals(""))
                {
                    e9.setError("enter password");
                }
                else if(passwrd.length()<6)
                {
                    e9.setError("Minimum 6 nos required");
                    e9.requestFocus();

                }
                else if(rpaswrd.equals(""))
                {
                    e10.setError("reenter password");
                }
                else if(rpaswrd.length()<6)
                {
                    e10.setError("Minimum 6 nos required");
                    e10.requestFocus();

                }
                else if(!passwrd.equals(rpaswrd))
                {
                    e10.setError("password mismatch");
                }
                else {

                    RequestQueue queue = Volley.newRequestQueue(rgistration.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/farmer_reg_form";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.

                            try {
                                JSONObject jo = new JSONObject(response);
                                String status = jo.getString("task");
//                                Toast.makeText(Login.this, status, Toast.LENGTH_SHORT).show();

                                if (status.equalsIgnoreCase("SUCCESS")) {

                                    Toast.makeText(rgistration.this, "REGISTERED SUCESSFULLY...!!!", Toast.LENGTH_SHORT).show();

                                    Intent
                                            in = new Intent(getApplicationContext(), Login.class);
                                    startActivity(in);
//                                    Intent i = new Intent(getApplicationContext(), LocationService.class);
//                                    startService(i);


                                } else {
                                    Toast.makeText(rgistration.this, status, Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                                Toast.makeText(rgistration.this, "" + e, Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(rgistration.this, "error" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("fname", fname);
                            params.put("lname", lname);
                            params.put("phone", phone);
                            params.put("post", post);
                            params.put("place", place);
                            params.put("pin", pin);
                            params.put("district", district);
                            params.put("uname", uname);
                            params.put("password", passwrd);


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