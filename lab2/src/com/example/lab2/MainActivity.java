package com.example.lab2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
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

	Mediator med;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);
		//add the Search function
		EditText textMessage = (EditText)findViewById(R.id.textMessage);
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

		listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {

				med.setGroup(groupPosition);
				med.setItem(-1);
				TextView tv1 = (TextView)findViewById(R.id.textMessage);
				med.printText(tv1);
				//				Toast.makeText(getApplicationContext(),
				//						listDataHeader.get(groupPosition) + " Expanded",
				//						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {


				med.setGroup(-1);
				med.setItem(-1);
				TextView tv1 = (TextView)findViewById(R.id.textMessage);
				med.printText(tv1);
				med.reset();

				//				Toast.makeText(getApplicationContext(),
				//						listDataHeader.get(groupPosition) + " Collapsed",
				//						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				TextView tv1 = (TextView)findViewById(R.id.textMessage);
				med.reset();
				med.setGroup(groupPosition);
				med.setItem(childPosition);

				med.printText(tv1);
				med.printExpandableItem(v,groupPosition,childPosition);


				//				TextView tv1 = (TextView)findViewById(R.id.textMessage);
				//				tv1.setText(listDataHeader.get(groupPosition)+"/" + listDataChild.get(
				//						listDataHeader.get(groupPosition)).get(
				//						childPosition));


				Toast.makeText(
						getApplicationContext(),
						listDataHeader.get(groupPosition)
						+ " : "
						+ listDataChild.get(
								listDataHeader.get(groupPosition)).get(
										childPosition), Toast.LENGTH_SHORT)
										.show();
				return false;
			}
		});
	}

	/*
	 * Preparing the list data
	 */

	private void searchForString(String s){


		String[] userInput=  s.split("\\/");

		Boolean hasChildren = false;

		//has substring?

		if(userInput.length>1){


			hasChildren=true;

		}

		else

			hasChildren=false;



		int headerPos = 0;

//		System.out.println("--------------------------------ny SÖK sträng-----------------");

		for (String key : listDataChild.keySet()){

			//check header for match

			boolean match = false;

			for (int i = 0; i < Math.min(Math.min(userInput[0].length(), 3), key.length()); i++) {

				if (key.charAt(i) != userInput[0].charAt(i)) {

					match = false;

					break;
				}

				else{

					if(userInput.length>1){

						match = true;

					}

					else{

						//call mediator

						match = false;

					}
				}

			}

			//check all children

			if(match==true){

				int sizeOfChildrenCurrentChildrenList =listDataChild.get(listDataHeader.get(headerPos)).size();

				for(int i=0; i<sizeOfChildrenCurrentChildrenList; i++){

					String currentName =listDataChild.get(listDataHeader.get(headerPos)).get(i);

					//System.out.println("currentName: "+currentName+ " - headerpos: "+headerPos+"sizeof child: "+sizeOfChildrenCurrentChildrenList);
					for (int j = 0; j < Math.min(Math.min(userInput[1].length(), 3), currentName.length()); j++) {

						if (currentName.charAt(Math.abs(j)) != userInput[1].charAt(Math.abs(j))) {

							match = false;

//		System.out.println("FAIL TO MATCH header: "+headerPos+" child: "+i);

						}

						else{

//							System.out.println("***substring MATCH at header: "+headerPos + "-and child: "+i+"**");
//
//							System.out.println(userInput[1]);
//
//							System.out.println("MATCH header: "+headerPos+" child: "+i);
//
//							System.out.println("Correct match header: "+headerPos+" child: "+i +  " key " +key + " currentName: " +currentName);
//
//							//call mediator
							
							med.setGroup(headerPos);
							med.setItem(i);
							View v = null;
							
							
							med.printExpandable(listAdapter,v, expListView );
							//med.printExpandableItem(convertView, groupPosition, childPosition)

						}
					}
				}
			}

			++headerPos;

		}
	}

	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Light");
		listDataHeader.add("Medium");
		listDataHeader.add("Dark");

		// Adding child data
		List<String> top250 = new ArrayList<String>();
		top250.add("green");
		top250.add("yellow");
		top250.add("red");
		top250.add("blue");


		List<String> nowShowing = new ArrayList<String>();
		nowShowing.add("green");
		nowShowing.add("yellow");
		nowShowing.add("red");
		nowShowing.add("blue");
		nowShowing.add("red");


		List<String> comingSoon = new ArrayList<String>();
		comingSoon.add("black");
		comingSoon.add("yellow");
		comingSoon.add("red");

		listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
		listDataChild.put(listDataHeader.get(1), nowShowing);
		listDataChild.put(listDataHeader.get(2), comingSoon);

		med = new Mediator(listDataHeader, listDataChild);
	}
}