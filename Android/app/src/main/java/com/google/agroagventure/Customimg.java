package com.google.agroagventure;

import android.content.Context;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class Customimg extends BaseAdapter {
    private Context context;

    ArrayList<String> a,b,c;

    SharedPreferences sh;


    public Customimg(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;

        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_customimg, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        ImageView tv1=gridView.findViewById(R.id.imageView2);
        TextView tv2=gridView.findViewById(R.id.textView15);
        TextView tv3=gridView.findViewById(R.id.textView16);





        tv2.setText(b.get(position));
        tv3.setText(c.get(position));




        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        String url1="http://" + sh.getString("ip", "") + ":5000/static/agri_equipments/"+(a.get(position));
//        Toast.makeText(context, "url=="+url1, Toast.LENGTH_LONG).show();
        Log.d("======>",url1);



        Picasso.with(context).load(url1).into(tv1);










        return gridView;

    }

}

