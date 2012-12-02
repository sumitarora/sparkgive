package com.sparkgive.components;

import java.util.ArrayList;

import com.sparkgive.R;
import com.sparkgive.RedeemActivity;
import com.sparkgive.R.id;
import com.sparkgive.R.layout;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StackAdapter extends ArrayAdapter<StackItem>   {
 
    private ArrayList<StackItem> items;
    private Context ctx;
   
 
    public StackAdapter(Context context, int textViewResourceId,
            ArrayList<StackItem> objects) {
        super(context, textViewResourceId, objects);
 
        this.items = objects;
        this.ctx = context;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.item, null);
        }
 
        StackItem m = items.get(position);
 
        if (m != null) {
                
                ImageView img = (ImageView)v.findViewById(R.id.imageView1);
                
                if (img != null) {
                		img.setImageDrawable(m.itemPhoto);
                    }
        }
       v.setOnClickListener(new OnClickListener(){
        	
            public void onClick(View arg0) {
            	Intent intent = new Intent(ctx, RedeemActivity.class);
            	String num = (arg0.getTag()).toString();
            	
            	intent.putExtra("tag", arg0.getTag().toString());
    		    ctx.startActivity(intent);
            
            }
       });
       v.setTag(position);
        return v;
    }

	
}