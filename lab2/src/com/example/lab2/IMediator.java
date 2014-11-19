package com.example.lab2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public interface IMediator {

	    public void printExpandable(ExpandableListAdapter view, View v, ViewGroup parent);
	    public void printExpandableItem(View convertView, int groupPosition, int childPosition);
	    public void printText(TextView convertView);
	    public void setItem(int item);
	    public void setGroup(int group);
	    
	  
	
}
