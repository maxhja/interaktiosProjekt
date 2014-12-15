package com.example.lab3;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class InteractiveSearchAdapter extends BaseAdapter {
    
	
	
	private final Context context;
	private List<String> searchList = new ArrayList<String>();
	// the context is needed to inflate views in getView()
    public InteractiveSearchAdapter(Context context,  List<String> theSearchList) {
        this.context = context;
        this.searchList = theSearchList;
    }
    
    public void updateList(List<String> str) {
        this.searchList = searchList;
        notifyDataSetChanged();
    }
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return searchList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return searchList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//ProposalView nameView = new ProposalView(context);
		//ItemView itemView = (ItemView)convertView;
        //if (null == nameView)
        	//nameView = nameView.inflate(parent);
            //nameView.setItem(getItem(position));
        
        return null;
		//return nameView;
		
	}
	
	

    

}
