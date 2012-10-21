package com.example.lowcost;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onPressAdd(View view) {
        // Do something in response to button
        	Intent intent = new Intent(this, Add.class);
        	startActivity(intent);
        }

    public void onPressView(View view) {
        // Do something in response to button
        	Intent intent = new Intent(this, ViewMap.class);
        	startActivity(intent);
        }
}
