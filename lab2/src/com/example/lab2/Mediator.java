package com.example.lab2;

import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;


public class Mediator implements IMediator {

	int itemId, groupId;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	
	public Mediator(List<String> dataHeader, HashMap<String, List<String>> dataChild ){
		listDataHeader = dataHeader;
		listDataChild = dataChild;
	}
	@Override
	public void setItem(int itm) {
		itemId = itm;
	}

	@Override
	public void setGroup(int grp) {
		groupId = grp;
	}
	
	@Override
	public void printExpandable(ExpandableListAdapter view,View v, ViewGroup parent) {

		//listDataHeader.indexOf(listDataChild.get(listDataHeader.get(groupId)).get(itemId));
		System.out.println(itemId + " " + groupId);
		
		//View groupView = view.getGroupView(groupId, false, v, parent);
		//TextView convert = (TextView) view.getChildView(groupId, itemId, false, v, parent).findViewById(R.id.lblListItem);
		//TextView convert = (TextView)itemView.findViewById(R.id.lblListItem);
		View itemView = parent.getChildAt(groupId);
	
		itemView.setBackgroundColor(Color.GREEN);
		
		listDataHeader.get(groupId);
	
	}
	
	public void printExpandableItem(View convertView, int groupPosition, int childPosition){

		TextView txtListChild = (TextView) convertView
				.findViewById(R.id.lblListItem);


		System.out.println(itemId + " = " + childPosition + " / " + groupId + " = " + groupPosition);
		if(itemId == childPosition && groupId == groupPosition){;
			txtListChild.setTypeface(null, Typeface.BOLD);
			txtListChild.setBackgroundColor(Color.GREEN);
		}
	}


	@Override
	public void printText(TextView tv1) {
		String item;
		String group;
		
		if(groupId == -1)
			group = "$";
		else
			group = listDataHeader.get(groupId);
		
		if(itemId == -1)
			item = "$";
		else
			item = listDataChild.get(listDataHeader.get(groupId)).get(itemId);
		
		tv1.setText(group + "/" + item);
			
		
	}
	
	public void reset(){
		
		itemId = -1;
			
	}
	
	




}
