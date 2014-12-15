package com.example.lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

public class InteractiveSearcher extends LinearLayout {

	EditText input;
	private List<String> searchList = new ArrayList<String>();
	
	public InteractiveSearcher(Context context) {
		super(context);
		
		input = new EditText(context);
		input.addTextChangedListener(new TextWatcher(){
	        @Override
	        public void afterTextChanged(Editable s) {

	        	loadWithAsync(s.toString()); //sends string to the async task.

	          }
	          @Override
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){
	            }
	          @Override
	        public void onTextChanged(CharSequence s, int start, int before, int count){

	          }
	      });
		
		addView(input);
		
		
		
		// setting list adapter
		//expListView.setAdapter(listAdapter);

		
		
		ProposalList ps = new ProposalList(context, searchList);
	
		// TODO Auto-generated constructor stub
	}
	
	
	
	
		private class NetWorker extends AsyncTask<String,Void,String> {
		    	
		    	// params comes from the execute() call: params[0] is the search Name.
		        @Override
		        protected String doInBackground(String... names) {
		        	//do here, parse here
		        	
		        	
		        	return doNetworkCall(names[0]);
		        }
	
		        @Override
		        protected void onPostExecute(String s) {
		        	
		        	System.out.println("ONPOST EXCEX");
		        	System.out.println(s);
		        }
		  }
		
		private String doNetworkCall(String inputString){
	    	String str = inputString;
	    	System.out.println("str");
	    	System.out.println(str);
	        try {
	            DefaultHttpClient httpclient = new DefaultHttpClient();
	            HttpGet httpget = new HttpGet("http://flask-afteach.rhcloud.com/getnames/3/"+str);
	            HttpResponse response = null;
	            response = httpclient.execute(httpget);

	            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            StringBuffer sb = new StringBuffer("");
	            String line = "";
	            String NL = System.getProperty("line.separator");
	            while ((line = in.readLine()) != null) {
	                sb.append(line + NL);
	            }
	            in.close();
	            String result = sb.toString();
	            return result;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return "";
	    }
	
		private void listPeolple(String s) throws JSONException{
		    	//List<String> nameList = new ArrayList<String>();
		    	JSONObject jObject = new JSONObject(s);
		    	JSONArray jArray = jObject.getJSONArray("result");
		    	System.out.println(jArray.length()+"<-------listPeolple--------------->");
		    	
		    	for (int i=0; i < jArray.length(); i++){
		    	    try {
		    	        String name = (String)jArray.get(i);
		    	        searchList.add(i, name.toLowerCase());
		    	        
		    	        System.out.println("oneObjectsItem");
		    	        System.out.println(name);
		    	    } catch (JSONException e) {
		    	        // Oops
		    	    }
		    	}
		    	
		    }

		private void loadWithAsync(String str) {
	    	System.out.println("str");
	    	System.out.println(str);
	    	NetWorker net= new NetWorker();
	        net.execute(str);
	    }

}
