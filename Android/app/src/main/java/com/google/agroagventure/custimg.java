package com.google.agroagventure;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class custimg extends BaseAdapter{
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;

    SharedPreferences sp;





    public custimg (Context applicationContext, ArrayList<String> a,ArrayList<String> b,ArrayList<String> c) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;

        sp=PreferenceManager.getDefaultSharedPreferences(applicationContext);



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
            gridView=inflator.inflate(R.layout.activity_custimg, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView4);

        ImageView tv3=(ImageView) gridView.findViewById(R.id.imageView);


        TextView tv2=(TextView)gridView.findViewById(R.id.textView5);




        tv1.setText(a.get(position));


        tv2.setText(c.get(position));




        tv1.setTextColor(Color.WHITE);


        tv2.setTextColor(Color.WHITE);






        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        String urll="http://"+sp.getString("ip","")+":5000/static/crop/"+b.get(position);
        java.net.URL thumb_u;
        try {
            thumb_u = new java.net.URL(urll);
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            tv3.setImageDrawable(thumb_d);



            //Picasso.with(Context)
            //    .load(urll)
            //  .transform(new Circulartransform())
            // .error(R.drawable.a)
            //  .into(img);



        }
        catch(Exception e){
            Log.d("*********",e.toString());
        }









        return gridView;

    }

}







