package com.google.agroagventure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class OrderAgricalturalEqupments extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1,b2,b3;
    String name,description,price,quantity,url,url2;

    SharedPreferences sh;
    String equi_id;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_agricaltural_equpments);
 sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1=findViewById(R.id.button7);
        b2=findViewById(R.id.button12);
        b3=findViewById(R.id.button17);

        e1=findViewById(R.id.editTextTextPersonName8);
        e2=findViewById(R.id.editTextTextPersonName2);
        e3=findViewById(R.id.editTextTextPersonName3);
        e4=findViewById(R.id.editTextTextPersonName7);
        img=findViewById(R.id.imageView);


        e1.setText(getIntent().getStringExtra("name"));
        e2.setText(getIntent().getStringExtra("description"));
        e3.setText(getIntent().getStringExtra("price"));
        equi_id=getIntent().getStringExtra("eqpmntid");




        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                RequestQueue queue1 = Volley.newRequestQueue(OrderAgricalturalEqupments.this);
                url2 = "http://" + sh.getString("ip", "") + ":5000/equpment_bill";

                // Request a string response from the provided URL.
                StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.

                        try {
                            JSONObject jo = new JSONObject(response);
                            String status = jo.getString("task");
                            String total=jo.getString("total");
                            SharedPreferences.Editor edt = sh.edit();
                            edt.putString("total", total);
                            edt.commit();



                            Toast.makeText(OrderAgricalturalEqupments.this, status, Toast.LENGTH_SHORT).show();

                            if (status.equalsIgnoreCase("success")) {



                                Toast.makeText(OrderAgricalturalEqupments.this, "Order Finished", Toast.LENGTH_SHORT).show();

                                Intent
                                        in = new Intent(getApplicationContext(), View_equipmentcart.class);
                                in.putExtra("b1","yes");
                                startActivity(in);


                            }

                            else
                            {


                                Toast.makeText(OrderAgricalturalEqupments.this, "FAILED...!!!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Log.d("=========", e.toString());
                            Toast.makeText(OrderAgricalturalEqupments.this, "" + e, Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(OrderAgricalturalEqupments.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<>();

                        params.put("fid", sh.getString("lid",""));
                        params.put("bil_equ_lid", sh.getString("oid",""));



                        return params;
                    }
                };
                // Add the request to the RequestQueue.
                queue1.add(stringRequest1);








            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ViewAgricalturalEqupments.class);
                startActivity(i);
            }
        });



        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        img.setImageDrawable(Drawable.createFromPath(getIntent().getStringExtra("photo")));
        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/static/agri_equipments/"+getIntent().getStringExtra("photo"));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            img.setImageDrawable(thumb_d);

        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }





        b1=findViewById(R.id.button7);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quantity=e4.getText().toString();

                if(quantity.equalsIgnoreCase(""))
                {
                    e1.setError("ENTER THE DETAILS...!!!");
                }
                else {


                    RequestQueue queue = Volley.newRequestQueue(OrderAgricalturalEqupments.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/addtocartequpment";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.

                            try {
                                JSONObject jo = new JSONObject(response);
                                String status = jo.getString("task");

                                String oid= jo.getString("orderid");

                                SharedPreferences.Editor edt = sh.edit();
                                edt.putString("oid", oid);
                                edt.commit();


                                Toast.makeText(OrderAgricalturalEqupments.this, status, Toast.LENGTH_SHORT).show();

                                if (status.equalsIgnoreCase("success")) {



                                    Toast.makeText(OrderAgricalturalEqupments.this, status, Toast.LENGTH_SHORT).show();

                                    Intent
                                            in = new Intent(getApplicationContext(),OrderAgricalturalEqupments.class);
                                    startActivity(in);


                                }

                                else
                                {


                                    Toast.makeText(OrderAgricalturalEqupments.this, "FAILED...!!!", Toast.LENGTH_SHORT).show();
                                }

                            } catch (Exception e) {
                                Log.d("=========", e.toString());
                                Toast.makeText(OrderAgricalturalEqupments.this, "" + e, Toast.LENGTH_SHORT).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(OrderAgricalturalEqupments.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("qty",quantity);
                            params.put("fid", sh.getString("lid",""));
                            params.put("eid",equi_id);


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

