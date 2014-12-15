package com.example.lab2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	EditText textMessage;

	Mediator med;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		//add the Search function
		textMessage = (EditText)findViewById(R.id.textMessage);
		textMessage.addTextChangedListener(new TextWatcher(){
			public void afterTextChanged(Editable s) {

				searchForString(s.toString());
			}
			public void beforeTextChanged(CharSequence s, int start, int count, int after){
			}
			public void onTextChanged(CharSequence s, int start, int before, int count){

			}
		}); 
		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild,med);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
				int groupPosition, long id) {
				
				med.setGroup(groupPosition);
				med.setItem(-1);
				TextView tv1 = (TextView)findViewById(R.id.textMessage);
				med.printText(tv1);
				listAdapter.notifyDataSetChanged();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

				
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				TextView tv1 = (TextView)findViewById(R.id.textMessage);
				med.setItem(-1);
				med.setGroup(-1);
				med.printText(tv1);
				
				listAdapter.notifyDataSetChanged();
			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				TextView tv1 = (TextView) findViewById(R.id.textMessage);
				
				
				med.setGroup(groupPosition);
				med.setItem(childPosition);
				med.printText(tv1);
							
				listAdapter.notifyDataSetChanged();

				
				return false;
			}
		});
	}

	/*
	 * SEE IF THE SEARCHWORD CONTAINS IN THE EXPANDABLELSIT
	 */
	int group = 0;
	boolean check = false;
	int checkLength = 0;
	private void searchForString(String s){
		
		String newS = s.toLowerCase();
		String[] userInput =  newS.split("\\/");
		
		int headerpos = -1;
		int groupID=-1;

		textMessage.setBackgroundColor(Color.WHITE);
		for(String key : listDataHeader){
			String newK = key.toLowerCase(Locale.getDefault());
			headerpos++;
			if(newK.equals(userInput[0])){
				groupID = headerpos;
				check = true;
				checkLength = newK.length();
				textMessage.setBackgroundColor(Color.WHITE);
			}
			if(check  &&  checkLength < userInput[0].length()){
				textMessage.setBackgroundColor(Color.RED);
			}
				
		}		
	
		int childID = -1;
	
		String[] temp = null;
		int counter = 0;
		int childPos = -1 ;

		if(groupID > -1){ //check if it has parent
			
			for(String strChild : listDataChild.get(listDataHeader.get(groupID))){
					
				String newStr = strChild.toLowerCase();
				childPos++;
			
				if(newStr.contains(userInput[userInput.length-1])){
					childID = childPos;
					med.setGroup(groupID);
					med.setItem(childID);
					expListView.expandGroup(groupID);
					listAdapter.notifyDataSetChanged();
						
				}
			}				
		}
	}
	
	public boolean contains( String haystack, String needle ) {
		  haystack = haystack == null ? "" : haystack;
		  needle = needle == null ? "" : needle;
		  // Works, but is not the best.
		  //return haystack.toLowerCase().indexOf( needle.toLowerCase() ) > -1
		  return haystack.toLowerCase().contains( needle.toLowerCase() );
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Light");
		listDataHeader.add("Medium");
		listDataHeader.add("Dark");

		// Adding child data
		List<String> light = new ArrayList<String>();
		light.add("green");
		light.add("yellow");
		light.add("red");
		light.add("blue");


		List<String> medium = new ArrayList<String>();
		medium.add("green");
		medium.add("yellow");
		medium.add("blue");
		medium.add("red");


		List<String> dark = new ArrayList<String>();
		dark.add("black");
		dark.add("yellow");
		dark.add("red");

		listDataChild.put(listDataHeader.get(0), light); // Header, Child data
		listDataChild.put(listDataHeader.get(1), medium);
		listDataChild.put(listDataHeader.get(2), dark);

		med = new Mediator(listDataHeader, listDataChild);
	}
}