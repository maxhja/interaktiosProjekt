package com.example.lab22;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

 
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseExpandableListAdapter {

private Context context;
private ArrayList<Continent> continentList;
private ArrayList<Continent> originalList;
private ArrayList<Continent> originalListCopy;
private String filterText="hej";
public boolean isFound = false; 
 
public MyListAdapter(Context context, ArrayList<Continent> continentList) {
 this.context = context;
 this.continentList = new ArrayList<Continent>();
 this.continentList.addAll(continentList);
 this.originalList = new ArrayList<Continent>();
 this.originalList.addAll(continentList);
}
 
@Override
public Object getChild(int groupPosition, int childPosition) {
 ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
 return countryList.get(childPosition);
}

@Override
public long getChildId(int groupPosition, int childPosition) {
 return childPosition;
}

@Override
public View getChildView(int groupPosition, int childPosition, boolean isLastChild, 
  View view, ViewGroup parent) {
  
 Country country = (Country) getChild(groupPosition, childPosition);
 if (view == null) {
  LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  view = layoutInflater.inflate(R.layout.child_row, null);
 }
 Log.v("Creatning new list 2", String.valueOf(childPosition)+" groupPosition: "+String.valueOf(groupPosition));
 TextView code = (TextView) view.findViewById(R.id.code);
 TextView name = (TextView) view.findViewById(R.id.name);
 
 TextView population = (TextView) view.findViewById(R.id.population);
 code.setText(country.getCode().trim());
 name.setText(country.getName().trim());
 
 /*hee*/
 String itemValue = country.getName().trim();

 System.out.println(isFound);

 if(itemValue.length()>1 && isFound){
	  
 
	  int startPos = itemValue.toLowerCase(Locale.US).indexOf(filterText.toLowerCase(Locale.US));
	  int endPos = startPos + filterText.length();
	  if (startPos != -1){ // This should always be true, just a sanity check{
	      Spannable spannable = new SpannableString(itemValue);
	      ColorStateList greenColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { Color.BLACK });
	      TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, greenColor, null);
	     
	      view.setBackgroundColor(Color.GREEN);
	  
	      spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	      name.setText(spannable);
	  }
	  else{
		  Spannable spannable = new SpannableString(itemValue);
	      ColorStateList colorRed = new ColorStateList(new int[][] { new int[] {}}, new int[] { Color.RED });
	      TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, colorRed, null);

	      spannable.setSpan(highlightSpan, 0, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	      name.setText(spannable);
		  name.setText(itemValue);
	  }
		 
 }
 population.setText(NumberFormat.getNumberInstance(Locale.US).format(country.getPopulation()));
  
 return view;
}

@Override
public int getChildrenCount(int groupPosition) {
  
 ArrayList<Country> countryList = continentList.get(groupPosition).getCountryList();
 return countryList.size();

}

@Override
public Object getGroup(int groupPosition) {
 return continentList.get(groupPosition);
}

@Override
public int getGroupCount() {
 return continentList.size();
}

@Override
public long getGroupId(int groupPosition) {
 return groupPosition;
}

@Override
public View getGroupView(int groupPosition, boolean isLastChild, View view,
  ViewGroup parent) {
  
 Continent continent = (Continent) getGroup(groupPosition);
 if (view == null) {
  LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  view = layoutInflater.inflate(R.layout.group_row, null);
 }
 
 
  
 TextView heading = (TextView) view.findViewById(R.id.heading);
 heading.setText(continent.getName().trim());
 
 String itemValue = continent.getName().trim();
 
 if(itemValue.length()>1){
	  
 
	  int startPos = itemValue.toLowerCase(Locale.US).indexOf(filterText.toLowerCase(Locale.US));
	  int endPos = startPos + filterText.length();
	  if (startPos != -1) // This should always be true, just a sanity check
	  {
	      Spannable spannable = new SpannableString(itemValue);
	      ColorStateList greenColor = new ColorStateList(new int[][] { new int[] {}}, new int[] { Color.GREEN });
	      TextAppearanceSpan highlightSpan = new TextAppearanceSpan(null, Typeface.BOLD, -1, greenColor, null);
	    
	      spannable.setSpan(highlightSpan, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	      heading.setText(spannable);
	  }
	  else
		  heading.setText(itemValue);
 }
  
 return view;
}

@Override
public boolean hasStableIds() {
 return true;
}

@Override
public boolean isChildSelectable(int groupPosition, int childPosition) {
 return true;
}
 
@TargetApi(Build.VERSION_CODES.GINGERBREAD) public void filterData(String query){
  
 query = query.toLowerCase();
 
 Log.v("namn", query);
 continentList.clear();
 
 filterText =query;
 if(query.isEmpty()){
  isFound = false;
  continentList.addAll(originalList);
 }
 else {
   
  for(Continent continent: originalList){
    
   ArrayList<Country> countryList = continent.getCountryList();
   ArrayList<Country> newList = new ArrayList<Country>();
   for(Country country: countryList){
    if(country.getCode().toLowerCase().contains(query) ||
        country.getName().toLowerCase().contains(query)){
   	 Log.v("Creatning new list 1", query);
   	 
   	 isFound = true;
   	 continentList.clear();
   	 continentList.addAll(originalList);
   	 
        //newList.add(country);
    }
   }
   if(newList.size() > 0){
    Continent nContinent = new Continent(continent.getName(),newList);
    continentList.add(nContinent);
   }
  }
 }
  
 Log.v("MyListAdapter", String.valueOf(continentList.size()));
 notifyDataSetChanged();
  
}

}
