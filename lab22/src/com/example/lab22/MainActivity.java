package com.example.lab22;

import android.support.v7.app.ActionBarActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import java.util.ArrayList;


import android.widget.Toast;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") public class MainActivity extends Activity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener{

	 private SearchView search;
	 private MyListAdapter listAdapter;
	 private ExpandableListView myList;
	 private ArrayList<Continent> continentList = new ArrayList<Continent>();
	  
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	         
	        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
				  search = (SearchView) findViewById(R.id.search);
				  
				  search.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
				  search.setIconifiedByDefault(false);
				  search.setOnQueryTextListener(this);
				  search.setOnCloseListener(this);
				  
				  

				 
	   
	  //display the list
	  displayList();
	  //expand all Groups
	  expandAll();
	 
	    }
	 
	   
	     
	    //method to expand all groups
	 private void expandAll() {
	  int count = listAdapter.getGroupCount();
	  for (int i = 0; i < count; i++){
	   myList.expandGroup(i);
	   
	  }
	 
	 }
	  
	 //method to expand all groups
	 private void displayList() {
	   
	  //display the list
	  loadSomeData();

	  //get reference to the ExpandableListView
	  myList = (ExpandableListView) findViewById(R.id.expandableList);
	  //create the adapter by passing your ArrayList data
	  listAdapter = new MyListAdapter(MainActivity.this, continentList);
	  //attach the adapter to the list
	  myList.setAdapter(listAdapter);
	   
	 }
	  
	 private void loadSomeData() {
	   
	  ArrayList<Country> countryList = new ArrayList<Country>();
	  Country country = new Country("", "Red",0);
	  countryList.add(country);
	  country = new Country("", "green",0);
	  countryList.add(country);
	  country = new Country("", "blue",0);
	  countryList.add(country);
	   
	  Continent continent = new Continent("Light",countryList);
	  continentList.add(continent);
	   
	  countryList = new ArrayList<Country>();
	  country = new Country("","black",0);
	  countryList.add(country);
	  country = new Country("","Gray",0);
	  countryList.add(country);
	  country = new Country("","Red",0);
	  countryList.add(country);
	   
	  continent = new Continent("Medium",countryList);
	  continentList.add(continent);
	  //third
	  ArrayList<Country> countryList3 = new ArrayList<Country>();
	  Country country3 = new Country("", "Red",0);
	  countryList3.add(country);
	  country = new Country("", "green",0);
	  countryList3.add(country);
	  country = new Country("", "blue",0);
	  countryList3.add(country);
	  country = new Country("", "black",0);
	  countryList3.add(country); 
	  
	  Continent continent3 = new Continent("Dark",countryList3);
	  continentList.add(continent3);
	   
	 }
	 
	 @Override
	 public boolean onClose() {
	  listAdapter.filterData("");
	  expandAll();
	  return false;
	 }
	 
	 @Override
	 public boolean onQueryTextChange(String query) {
	  listAdapter.filterData(query);
	  expandAll();
	  return false;
	 }
	 
	 @Override
	 public boolean onQueryTextSubmit(String query) {
	  listAdapter.filterData(query);
	  expandAll();
	  return false;
	 }
}
