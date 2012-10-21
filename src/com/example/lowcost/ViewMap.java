package com.example.lowcost;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Menu;

public class ViewMap extends MapActivity {

	private MapController mapController;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmap);
         
        //     Intent intent = getIntent();
        //     String message = intent.getStringExtra(Track.EXTRA_MESSAGE);
        //     int cX = intent.getIntExtra(Track.EXTRA_GEOx, 0);
        //     int cY = intent.getIntExtra(Track.EXTRA_GEOy, 0);
             
             MapView mapView = (MapView) findViewById(R.id.mapview);
             mapView.setBuiltInZoomControls(true);
             mapController = mapView.getController();
             mapController.setZoom(4);  
             //mapController.animateTo(new GeoPoint(cX,cY));
        
             List<Overlay> mapOverlays = mapView.getOverlays();
             Drawable drawable = this.getResources().getDrawable(R.drawable.position);
             
        Cursor c;
        DataBaseHelper databasehelper = new DataBaseHelper(this);
        SQLiteDatabase db = databasehelper.getReadableDatabase();
        if(db != null){
        	
        	String[] campos = new String[] {"nombre", "ciudad"};
        	 
        	 c = db.query("tabla", campos, null, null, null, null, null);
        	 if (c.moveToFirst()) {
        	     //Recorremos el cursor hasta que no haya más registros
        	     do {
        	          String nombre = c.getString(0);
        	          String ciudad = c.getString(1);
        	         
        	          Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        	          int x;
        	          int y;
        	          
        	          try {	
        	          	
        	  	          List<Address> addresses = geocoder.getFromLocationName(ciudad, 1);
        	  	          //String strCompleteAddress = "";
        	  	          if (addresses.size() > 0) {
        	  	          //GeoPoint p = new GeoPoint(
        	  	           x = (int) (addresses.get(0).getLatitude() * 1E6);
        	  	           y = (int) (addresses.get(0).getLongitude() * 1E6);
        	  	          
        	  	         
        	  	           HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
        	          
        	  	           GeoPoint point = new GeoPoint(x,y); 
        	             
        	  	           OverlayItem overlayitem = new OverlayItem(point, "Hey There " + nombre + "!", "You're in " + ciudad + " City!");
        	  	           itemizedoverlay.addOverlay(overlayitem);
        	  	           mapOverlays.add(itemizedoverlay);
        	  	          }
        	          } catch (IOException e) {
    	  	              e.printStackTrace();
    	  	           }
        	          
        	     } while(c.moveToNext());
        	}
        	
        }

        db.close();

  
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_viewmap, menu);
        return true;
    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

}
