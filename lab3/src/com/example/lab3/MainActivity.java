package com.example.lab3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity {
	TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //creates the search view
       // InteractiveSearcher is = new InteractiveSearcher(this);
        
        setContentView(R.layout.activity_main);
    }
}
