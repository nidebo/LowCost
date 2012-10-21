package com.example.lowcost;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.MapActivity;

public class Track extends MapActivity {
	
	public final static String EXTRA_GEOx = "geox";
	public final static String EXTRA_GEOy = "geoy";
	public final static String EXTRA_MESSAGE = "city";
	
	Geocoder geocoder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_track, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

    public void trackCity(View view) {
    	int x;
    	int y;
        // Do something in response to button
    	TextView tt = (TextView) findViewById(R.id.track_city);
    	if(tt != null && !tt.equals("")){
        geocoder = new Geocoder(this, Locale.getDefault()); 
        try {
        List<Address> addresses = geocoder.getFromLocationName(tt.getText().toString(), 1);
        //String strCompleteAddress = "";
        if (addresses.size() > 0) {
        //GeoPoint p = new GeoPoint(
         x = (int) (addresses.get(0).getLatitude() * 1E6);
         y = (int) (addresses.get(0).getLongitude() * 1E6);
        Intent intent = new Intent(this, ViewMap.class);
     	String message = tt.getText().toString();
     	intent.putExtra(EXTRA_MESSAGE, message);
     	intent.putExtra(EXTRA_GEOx, x);
     	intent.putExtra(EXTRA_GEOy, y);
     	startActivity(intent);
        }
        } catch (IOException e) {
        e.printStackTrace();
        }

    	}
    	else Toast.makeText(this, "INPUT SOMETHING!", 5000).show();
    }
    

}
