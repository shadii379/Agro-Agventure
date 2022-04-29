package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
public class Login extends AppCompatActivity {
    EditText e1,e2;
    Button b1;

    TextView tv1;
    String url;
    SharedPreferences sh;
String uname,passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=findViewById(R.id.editTextTextPersonName9);
        e2=findViewById(R.id.editTextTextPassword3);
        b1=findViewById(R.id.button4);

        tv1=findViewById(R.id.textView5);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname = e1.getText().toString();
                passwd = e2.getText().toString();
                if(uname.equals(""))
                {
                    e1.setError("enter username");
                }
                else if(passwd.equals(""))
                {
                    e2.setError("enter password");
                }
                else {

                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/login";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.

                            try {
                                JSONObject jo = new JSONObject(response);
                                String status = jo.getString("task");
//                                Toast.makeText(Login.this, status, Toast.LENGTH_SHORT).show();

                                if (status.equalsIgnoreCase("ok")) {
                                    String lid = jo.getString("id");

                                    SharedPreferences.Editor edt = sh.edit();
                                    edt.putString("lid", lid);
                                    edt.commit();
                                    Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();

                                    Intent
                                            in = new Intent(getApplicationContext(), farmerHome.class);
                                    startActivity(in);
//                                    Intent i = new Intent(getApplicationContext(), LocationService.class);
//                                    startService(i);


                                } else {
                                    Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                                }
                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                                Toast.makeText(Login.this, "" + e, Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("uname", uname);
                            params.put("password", passwd);


                            return params;
                        }
                    };
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }



            }

        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(getApplicationContext(), rgistration.class);
                startActivity(i2);
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}