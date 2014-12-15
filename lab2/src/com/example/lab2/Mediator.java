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
	
	public int getItem(){
		return itemId;
	}
	public int getGroup(){
		return groupId;
	}
	
	public String getGroupName(){
		return listDataHeader.get(groupId);
	}
	
	
	public String  getItemName(){
		String tmp;
		if(itemId == -1){
			tmp = "-1";
		}else{
			String key = listDataHeader.get(groupId);
			List<String> value = listDataChild.get(key);
			
			tmp = value.get(itemId).toString();
		}
		return tmp;
	}

	@Override
	public void printText(TextView tv1) {
		String item;
		String group;
		
		System.out.println("Med 1");
		if(groupId == -1)
			group = "%";
		else
			group = listDataHeader.get(groupId);
		
		if(itemId == -1)
			item = "%";
		else
			item = listDataChild.get(listDataHeader.get(groupId)).get(itemId);
		
		tv1.setText(group + "/" + item);
			
		
	}

}
