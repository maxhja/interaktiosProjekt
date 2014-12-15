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

import com.example.lab3.InteractiveSearcher;
import com.example.lab3.R;
import com.example.lab3.MainActivity.NetWorker;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class MainActivity extends Activity {
    EditText result;
    RelativeLayout rl;
    View vw = null;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (EditText) findViewById(R.id.editText);
        Button n = (Button) findViewById(R.id.button);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                interactiveSearch();
           
            }	
        });
        
    }

    public void setLayout(View vw){
    	
    			RelativeLayout ll = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.interactive_searcher, null);
    			// tv = new TextView(this);
    			//tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    			//tv.setText(vw);
    			//ll.addView(tv);
    			

    			addContentView(ll, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
    					ViewGroup.LayoutParams.FILL_PARENT));
    			
    			
    			
    }
	public void interactiveSearch() {
		
		String[] dataString = new String[]{"EMMY","EMELIA","EMMA"};
	    InteractiveSearcher is = new InteractiveSearcher(this.getApplicationContext());
	    is.setData(dataString);
	    setLayout(vw);
	   // return v;
	}

    private void loadData() {
        loadWithThread();
        //loadWithAsync();
    }

    private void loadWithAsync() {
        new NetWorker().execute();
    }

    private void loadWithThread(){
         Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final String data = doNetworkCall();
                result.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(data);
                    }
                });
            }
        });
        t.start();
    }

    private String doNetworkCall(){
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet("http://flask-afteach.rhcloud.com/getnames/3/Emm");
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

    private class NetWorker extends AsyncTask<Void,Void,String> {


        @Override
        protected String doInBackground(Void... params) {
            return doNetworkCall();
        }

        @Override
        protected void onPostExecute(String s) {
            result.setText(s);
        }
    }

}