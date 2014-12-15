package com.example.lab3;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

class MyAddapter extends BaseAdapter {
        Context context;
        ArrayList<String> data;
        MyAddapter(Context ctx, ArrayList<String> stringArray){
        	data = stringArray;
        	context = ctx;
        }
        
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Object getItem(int position) {
			
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {


			CustomView cw = new CustomView(context, null);
			
			return cw;
		}
        

    }