package com.example.lab3;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.LinearLayout;

public class ProposalList extends LinearLayout { 

	private List<String> propsalNames = new ArrayList<String>();
	private final Context context;
	
	public ProposalList(Context context, List<String> propsalNames) {
		super(context);
		this.propsalNames = propsalNames;
		this.context = context;
		// TODO Auto-generated constructor stub
		createList();
	}
	
	
	void createList(){
		
		 System.out.println("inside the proposal");
		
		for (int i=0; i<propsalNames.size(); i++) {
		    System.out.println(propsalNames.get(i));
		}
		
	}

}
