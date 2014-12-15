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

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.TextView;

@SuppressLint("NewApi") public class InteractiveSearcher extends EditText {
    
 Context context;
 AttributeSet attr;
 private ArrayList<String> listArray = new ArrayList<String>();
 
 private ArrayAdapter<String> adapter;
 private List<String> searchList = new ArrayList<String>();
 private ArrayAdapter<CustomView> arrayAdapter;
 private String str="";
 
 LinearLayout layoutOfPopup; 
 ListPopupWindow popupWindow;
 Button popupButton, insidePopupButton; 
 TextView popupText;
 
 
 
 public InteractiveSearcher(Context context) {
  super(context);
  this.context = context;
  
  init(context);
  
         
 }
 public InteractiveSearcher(Context context, AttributeSet attrs) {
  super(context);
  this.context = context;
  this.attr = attrs;

  init(context);    
  
 }
 public InteractiveSearcher(Context context, AttributeSet attrs, int defStyle) {
     super(context, attrs, defStyle);
     this.context = context;
     
     init(context);
 }

 @Override
 public void setText(CharSequence text, BufferType type) {
  //System.out.println("setText");
  
  super.setText(text, type);
 };
 

 @Override
 public Editable getText() {
 // loadWithAsync(super.getText().toString());
  popupWindow.show();
  return super.getText();
 }
 
 
 public void init(Context context) {
	 ArrayAdapter<CustomView> aa = new ArrayAdapter<CustomView>(context,R.layout.list_item);
	 aa.add(new CustomView(context,"ok"));
	 popupWindow = new ListPopupWindow(context);
	 popupWindow.setAdapter(aa);
	 
	 popupWindow.setWidth(700);
	 popupWindow.setHeight(400);
     popupWindow.setModal(true);
     popupWindow.setAnchorView(this);
 
     
    // addView(popupWindow);
 }

 
 public void popupInit(Context context) {
  //popupButton.setOnClickListener( context); 
  //insidePopupButton.setOnClickListener((OnClickListener) context); 
  //popupMessage = new PopupWindow(layoutOfPopup, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT); 
  //popupMessage.setContentView(layoutOfPopup);
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
           
           //System.out.println("ONPOST EXCEX");
           System.out.println(s);
           try {
      parseJSONList(s);
     } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
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
 
private void parseJSONList(String s) throws JSONException{
	

       listArray.clear();


       JSONObject jObject = new JSONObject(s);
       JSONArray jArray = jObject.getJSONArray("result");
       for (int i=0; i < jArray.length(); i++){
           try {
               String name = (String)jArray.get(i);

           } catch (JSONException e) {
               // Oops
           }
       }       
      


    	
 }
 
 private void loadWithAsync(String str) {
     
      //System.out.println(str);
      NetWorker net= new NetWorker();
       net.execute(str);
  }

}