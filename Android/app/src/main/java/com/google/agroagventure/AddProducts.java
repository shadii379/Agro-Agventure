package com.google.agroagventure;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AddProducts extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1,b2;
    TextView tv;
    String name,description,qty,price;
    SharedPreferences sh;
    String url="";
    String PathHolder="",title="";
    byte[] filedt=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1=findViewById(R.id.editTextTextPersonName4);
        e2=findViewById(R.id.editTextTextPersonName5);
        e3=findViewById(R.id.editTextTextPersonName11);
        e4=findViewById(R.id.editTextTextPersonName6);

        tv=findViewById(R.id.textView63);

        b1=findViewById(R.id.button6);

        b2=findViewById(R.id.button2);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
//            intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 7);



            }

        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=e1.getText().toString();
                description=e2.getText().toString();
                qty=e3.getText().toString();
                price=e4.getText().toString();

                if(name.equals(""))
                {
                    e1.setError("enter name");
                }
                else if(description.equals(""))
                {
                    e2.setError("enter description");
                }
                else if (PathHolder.equals(""))
                {
                    tv.setError("pls select file");
                }
                else if(qty.equals(""))
                {
                    e3.setError("enter qty");
                }

                else if (price.equals(""))
                {
                    e4.setError("enter price");
                }
                else {


                    uploadBitmap(title);

                }

            }
        });


    }





    ProgressDialog pd;
    private void uploadBitmap(final String title) {
        pd=new ProgressDialog(AddProducts.this);
        pd.setMessage("Uploading....");
        pd.show();
        url = "http://" + sh.getString("ip", "") + ":5000/add_products";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
                        pd.dismiss();
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {

                                Toast.makeText(AddProducts.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),ViewProducts.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();


                params.put("name",name);
                params.put("description",description);
                params.put("qty",qty);
                params.put("price",price);
//                params.put("place",place);
//                params.put("post",post);
//                params.put("pin",pin);
//                params.put("father",father);
//                params.put("mother",mother);
//                params.put("quardn",quardn);
//                params.put("relatn",relatn);
                params.put("lid",sh.getString("lid",""));

                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("file", new DataPart(PathHolder , filedt ));









                return params;
            }
        };

        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();

                    // Get the path
                    try {
                        PathHolder = FileUtils.getPathFromURI(this, uri);
//                        PathHolder = data.getData().getPath();
//                        Toast.makeText(this, PathHolder, Toast.LENGTH_SHORT).show();

                        filedt = getbyteData(PathHolder);

//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                        tv.setText(PathHolder);
                    }
                    catch (Exception e){
                        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private byte[] getbyteData(String pathHolder) {

        File fil = new File(pathHolder);
        int fln = (int) fil.length();
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(fil);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[fln];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            byteArray = bos.toByteArray();
            inputStream.close();
        } catch (Exception e) {
        }
        return byteArray;




    }





}

